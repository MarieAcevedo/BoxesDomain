/**
 * Copyright 2013 Marie Acevedo (http://www.sojanddesign.com)
 * This file is part of Framework So'D Cube (http://www.sojanddesign.com/Sodcube).
 *
 * Framework So'D Cube is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Framework So'D Cube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Framework So'D Cube.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sojanddesign.boxes.domain.model.user;

import com.sodcube.domain.exception.DomainException;

/**
 * Class factory of user's aggregates
 * 
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 31 janv. 2013 - 0.1
 */
public class UserFactory {

	/**
	 * unique instance of singleton UserFactory
	 */
	private volatile static UserFactory uniqueInstance;
	private final UserRepository userRepository;

	/**
	 * instantiable once (singleton)
	 */
	private UserFactory(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Creates once a UserFactory using given repository for user aggregate
	 * 
	 * @param userRepository
	 *            repository managing user
	 * @return unique instance of UserFactory
	 */
	public static UserFactory getInstance(final UserRepository userRepository) {
		if (uniqueInstance == null) {
			synchronized (UserFactory.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new UserFactory(userRepository);
				}
			}
		}
		return uniqueInstance;
	}

	/**
	 * Method factory for aggregate user using given email, user name and
	 * password
	 * 
	 * @param email
	 * @param userName
	 * @param password
	 * @return user just created by factory
	 * @throws DomainException
	 *             if user can not be created
	 */
	public User createUser(final String email, final String userName,
			final String password) throws DomainException {
		//check if email is not already use by Boxes
		User user = userRepository.findByEmail(email);
		if(user != null){
			throw new UserAlreadyExistException("A user is already registered for email adress : " +email);
		}
		user = new User(userRepository.nextUserId());
		AccountSettings settings = new AccountSettings(email, userName, password);
		user.setAccountSettings(settings);
		return user;
	}
}

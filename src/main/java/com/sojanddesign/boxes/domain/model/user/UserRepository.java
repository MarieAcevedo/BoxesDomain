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


/**
 * UserRepository is class to manage lyfe cycle of {@link User}
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 31 janv. 2013 - 0.1
 */
public interface UserRepository {
	
	/**
	 * Finds a user using given id
	 * 
	 * @param id
	 *            user identifier
	 * @return User if is found, else {@code null}
	 */
	User find(UserId id);

	/**
	 * Saves given user
	 * 
	 * @param user
	 *            user to save
	 */
	void store(User user);

	/**
	 * @return A unique, generated user Id.
	 */
	UserId nextUserId();

	/**
	 * Finds a user using given email
	 * @param email email that must be found
	 * @return User if is found, else {@code null}
	 */
	User findByEmail(String email);

}

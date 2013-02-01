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

import com.sodcube.domain.core.DomainObject;
import com.sodcube.domain.core.ValueObject;

/**
 * TODO AccountSettings
 * 
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 31 janv. 2013 - 0.1
 */
public class AccountSettings extends ValueObject {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = -2171903241789836567L;
	private final String email;
	private final String userName;
	private final String password;

	/**
	 * default constructor
	 * @param email 
	 * @param userName
	 * @param password
	 */
	public AccountSettings(String email, String userName, String password) {
		this.email = email;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * @see com.sodcube.domain.core.DomainObject#isSameAs(com.sodcube.domain.core.DomainObject)
	 */
	@Override
	public boolean isSameAs(DomainObject dob) {
		boolean isSameAs = false;
		if(dob != null && dob instanceof AccountSettings){
			isSameAs = email.equalsIgnoreCase(((AccountSettings)dob).getEmail());
			isSameAs = isSameAs && userName.equalsIgnoreCase(((AccountSettings)dob).getUserName());
			isSameAs = isSameAs && password.equalsIgnoreCase(((AccountSettings)dob).getPassword());
		}
		return isSameAs;
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * @return the userName
	 */
	public final String getUserName() {
		return userName;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

}

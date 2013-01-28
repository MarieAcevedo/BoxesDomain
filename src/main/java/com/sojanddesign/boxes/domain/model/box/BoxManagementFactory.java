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
package com.sojanddesign.boxes.domain.model.box;

/**
 * Simple factory of BoxManagementPolicy.
 * 
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 25 Jan. 2013 - 0.1
 */
final class BoxManagementFactory {

	/**
	 * Default private constructor
	 */
	private BoxManagementFactory() {
	}

	/**
	 * Creating BoxManagementPolicy from the box specified
	 * @param box box to manage
	 * @return the BoxManagementPolicy created
	 */
	final static BoxManagementPolicy createBoxManagementPolicy(Box box) {
		BoxManagementPolicy policy = null;
		switch (box.getType()) {
		case INBOX:
			policy = new InboxManagementPolicy(box);
			break;

		default:
			policy = new InboxManagementPolicy(box);
			break;
		}
		return policy;
	}
}

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

import java.util.List;

import com.sojanddesign.boxes.domain.model.user.User;

/**
 * BoxRepository is class to manage lyfe cycle of {@link Box}, and {@link Element}
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 28 Jan. 2013 - 0.1
 * @deprecated Box is not aggregate
 */
public interface BoxRepository {

	/**
	 * Finds all boxes for the specified user.
	 * 
	 * @param user
	 *            user witch is owner of boxes
	 * @return All boxes
	 */
	List<Box> findAllByUser(User user);

	/**
	 * Finds a box using given id
	 * 
	 * @param id
	 *            box identifier
	 * @return Box if is found, else {@code null}
	 */
	Box find(BoxId id);

	/**
	 * Saves given box
	 * 
	 * @param box
	 *            box to save
	 */
	void store(Box box);

	/**
	 * Saves all boxes
	 */
	void storeAll();

	/**
	 * @return A unique, generated box Id.
	 */
	BoxId nextBoxId();

}

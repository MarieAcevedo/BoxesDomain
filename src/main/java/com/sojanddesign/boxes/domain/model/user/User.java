/**
 * Copyright 2013 Marie Acevedo (http://www.sojanddesign.com)
 * This file is part of Boxes Domain (http://www.sojanddesign.com/Boxes).
 *
 * Boxes Domain is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Boxes Domain is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Boxes Domain.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sojanddesign.boxes.domain.model.user;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Instant;

import com.sodcube.domain.core.Entity;
import com.sodcube.domain.exception.DomainException;
import com.sojanddesign.boxes.domain.model.box.Box;
import com.sojanddesign.boxes.domain.model.box.BoxId;
import com.sojanddesign.boxes.domain.model.box.TypeBox;
import static com.sojanddesign.boxes.domain.model.box.TypeBox.*;

/**
 * The system user is a root entity of user's aggregates. It's the owner of the
 * boxes.
 * 
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 24 jan. 2013 - 0.1
 * 
 */
public class User extends Entity<UserId> {

	/**
	 * 
	 */
	private static final int NB_INITIAL_BOXES = 5;
	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = 6759287467150584116L;
	private static final int NB_ACTIVATION_DAY_MAX = 3;
	private List<Box> boxes;
	private AccountSettings accountSettings;
	/**
	 * true if registration user has been validated by mail
	 */
	private boolean active;
	
	/**
	 * Default constructor with uniqueIdentifier
	 * 
	 * @param uniqueIdentifier
	 *            {@link UserId}
	 * @throws DomainException 
	 */
	public User(UserId uniqueIdentifier) throws DomainException {
		super(uniqueIdentifier);
		active = false;
	}

	/**
	 * @return the boxes
	 */
	public List<Box> getBoxes() {
		return boxes;
	}


	/**
	 * @param boxes
	 *            the boxes to set
	 */
	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}

	/**
	 * @return the accountSettings
	 */
	public final AccountSettings getAccountSettings() {
		return accountSettings;
	}

	/**
	 * @param accountSettings the accountSettings to set
	 */
	public final void setAccountSettings(AccountSettings accountSettings) {
		this.accountSettings = accountSettings;
	}

	/**
	 * @return the active
	 */
	public final boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public final void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Active the current user and initialize default boxes for him.
	 * @throws ExpirationActivationUserException if creation date is greater than {@code NB_ACTIVATION_DAY_MAX}
	 */
	public void activate() throws DomainException {
		int nbDay = Days.daysBetween(new DateTime(this.accountSettings.getCreationDate()),Instant.now()).getDays();
		if(nbDay > NB_ACTIVATION_DAY_MAX){
			active = false;
			throw new ExpirationActivationUserException("The activation request has exceeded the period of " + NB_ACTIVATION_DAY_MAX +" days for "+accountSettings.getEmail());
		}
		intializeDefaultBoxes();
		active = true;
	}

	/**
	 * Default boxes are intializing with : inbox, trash, next actions, less than two minutes actions and incubator. 
	 * @throws DomainException 
	 */
	private void intializeDefaultBoxes() throws DomainException {
		if (boxes == null){
			boxes = new ArrayList<Box>(NB_INITIAL_BOXES);
		}
		boxes.add(new Box(generateBoxId(INBOX), "inbox", this, INBOX));
		boxes.add(new Box(generateBoxId(TRASH), "trash", this, TRASH));
		boxes.add(new Box(generateBoxId(NEXT_ACTIONS), "next actions", this, NEXT_ACTIONS));
		boxes.add(new Box(generateBoxId(LESS_THAN_TWO_MINUTES_ACTIONS), "less than two minutes actions", this, LESS_THAN_TWO_MINUTES_ACTIONS));
		boxes.add(new Box(generateBoxId(INCUBATOR), "incubator", this, INCUBATOR));
	}
	
	/**
	 * Generate BoxId like [TypeBox]-[UserId.id]-[position in list]
	 * @param type type of the box
	 * @return id of the creating box
	 * @throws DomainException if id is {@code null}
	 */
	private BoxId generateBoxId(final TypeBox type) throws DomainException{
		final String separator = "-";
		StringBuffer id = new StringBuffer(type.toString());
		id.append(separator);
		id.append(getUniqueIdentifier().getId());
		id.append(separator);
		id.append(boxes.size()+1);
		return new BoxId(id.toString());
	}


}
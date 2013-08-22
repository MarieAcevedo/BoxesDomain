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

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import com.sojanddesign.boxes.domain.model.box.DomainBoxesCollectSteps;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

/**
 * TODO BoxesUserCreate
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 30 janv. 2013 - 0.1
 */
@RunWith(JUnitReportingRunner.class)
public class BoxesUserCreate extends JUnitStories {

	public BoxesUserCreate() {
		super();
	}
 
	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new DomainBoxesUserCreateSteps(), new DomainBoxesUserActivateSteps(),new DomainBoxesCollectSteps());
	}
 
	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("com/sojanddesign/boxes/domain/model/user/boxesdomain_user_create.story","com/sojanddesign/boxes/domain/model/user/boxesdomain_user_activateaccount.story","com/sojanddesign/boxes/domain/model/box/boxesdomain_elements_collect.story");
	}

}

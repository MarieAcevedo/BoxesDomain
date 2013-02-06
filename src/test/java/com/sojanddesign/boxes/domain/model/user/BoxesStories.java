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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;

/**
 * TODO BoxesStories
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 6 févr. 2013 - 0.1
 */
public class BoxesStories extends JUnitStories{

	  private final CrossReference xref = new CrossReference();
	  
	    public BoxesStories() {
	        configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(false)
	                .doIgnoreFailureInView(true).doVerboseFailures(true).useThreads(2).useStoryTimeoutInSecs(60);
	        //configuredEmbedder().useEmbedderControls(new PropertyBasedEmbedderControls());
	    }
	 
	    @Override
	    public Configuration configuration() {
	        Class<? extends Embeddable> embeddableClass = this.getClass();
	        Properties viewResources = new Properties();
	        viewResources.put("decorateNonHtml", "true");
	        viewResources.put("reports", "ftl/jbehave-reports-with-totals.ftl");
	        // Start from default ParameterConverters instance
	        ParameterConverters parameterConverters = new ParameterConverters();
	        // factory to allow parameter conversion and loading from external resources (used by StoryParser too)
	        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(), new LoadFromClasspath(embeddableClass), parameterConverters);
	        // add custom converters
	        parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
	                new ExamplesTableConverter(examplesTableFactory));
	        return new MostUsefulConfiguration()
	            .useStoryLoader(new LoadFromClasspath(embeddableClass))
	            .useStoryParser(new RegexStoryParser(examplesTableFactory)) 
	            .useStoryReporterBuilder(new StoryReporterBuilder()
	                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
	                .withDefaultFormats()
	                .withViewResources(viewResources)
	                .withFailureTrace(true)
	                .withFailureTraceCompression(true)                
	                .withCrossReference(xref)) 
	            .useParameterConverters(parameterConverters)                     
	            // use '%' instead of '$' to identify parameters
	            .useStepPatternParser(new RegexPrefixCapturingPatternParser(
	                            "%")) 
	            .useStepMonitor(xref.getStepMonitor());                               
	    }
	 
	    @Override
	    public InjectableStepsFactory stepsFactory() {
	        return new InstanceStepsFactory(configuration(), new DomainBoxesUserCreateSteps(), new DomainBoxesUserActivateSteps());
	    }
	     
	    @Override
	    protected List<String> storyPaths() {
	        // Specify story paths as URLs
			return Arrays.asList("com/sojanddesign/boxes/domain/model/user/boxesdomain_user_create.story","com/sojanddesign/boxes/domain/model/user/boxesdomain_user_activateaccount.story");
	    }
}

package io.fabric8;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;


public class WebPageMessageGeneratorTest {

    @Test
    public void testWelcomeMessage(){
       assertTrue(WebPageMessageGenerator.getWelcomeMessage().contains("pipeline-test-project-dependency"));
    }
}

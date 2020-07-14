package helpers;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CommonListener extends RunListener{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonListener.class);
    //Start and End time of the tests
    long startTime;
    long endTime;

    @Override
    public void testRunStarted(Description description) throws Exception {
        //Start time of the tests
        startTime = new Date().getTime();
        //Print the number of tests before the all tests execution.
        LOGGER.info("Tests started! " + "\n");
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        //End time of the tests
        endTime = new Date().getTime();
        //Print the below lines when all tests are finished.
        LOGGER.info("Tests finished! Number of test case: " + result.getRunCount());
        long elapsedSeconds = (endTime-startTime)/1000;
        LOGGER.info("Elapsed time of tests execution: " + elapsedSeconds +" seconds");
    }

    @Override
    public void testStarted(Description description) throws Exception {
        //Write the test name when it is started.
        LOGGER.info( "Test : " + description.getMethodName() + " is starting...");
    }

    @Override
    public void testFinished(Description description) throws Exception {
        //Write the test name when it is finished.
        LOGGER.info( "Test : " + description.getMethodName() + " is finished...");
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        //Write the test name when it is failed.
        LOGGER.info(failure.getDescription().getMethodName() + " test FAILED!!!");
    }

    /**
     *  Called when a test will not be run, generally because a test method is annotated with Ignore.
     * */
    public void testIgnored(Description description) throws java.lang.Exception
    {
        LOGGER.info("Execution of test case ignored : "+ description.getMethodName());
    }

}

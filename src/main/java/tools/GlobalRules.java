package tools;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import static tools.DriverSetup.actions;

public class GlobalRules {
    @AfterMethod
    public void onFail(ITestContext context, ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            actions.takeScreenshot();
        }
    }
}

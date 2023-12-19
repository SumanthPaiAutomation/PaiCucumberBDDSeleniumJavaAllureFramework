package Utilities;

import Managers.AllDriverManager;

public class TestContext {

    private final AllDriverManager driverManager;
    ;
    public ScenarioContext scenarioContext;

    public TestContext() {
        driverManager = new AllDriverManager();
        //scenarioContext = new ScenarioContext();
    }

    public AllDriverManager getDriverManager() {
        return driverManager;
    }

//    public ScenarioContext getScenarioContext() {
//        return scenarioContext;
//    }
}

package org.operaton.community.process_test_coverage.tests.junit4.platform7.model;

import org.operaton.bpm.engine.TaskService;
import org.operaton.bpm.engine.task.Task;
import org.operaton.bpm.engine.test.Deployment;
import org.operaton.bpm.engine.variable.Variables;
import org.operaton.community.process_test_coverage.junit4.platform7.rules.TestCoverageProcessEngineRule;
import org.operaton.community.process_test_coverage.junit4.platform7.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class CoverageRatioTest {

    @Rule
    @ClassRule
    public static TestCoverageProcessEngineRule rule
        = TestCoverageProcessEngineRuleBuilder.create().assertClassCoverageAtLeast(1.0).build();

    public void completeTask() {
        TaskService taskService = rule.getProcessEngine().getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        taskService.complete(task.getId());
    }

    @Test
    @Deployment(resources = "coverage-ratio-test.bpmn")
    public void testCoverageRatio_Ok() {
        rule.getRuntimeService().startProcessInstanceByKey("testCoverageRatio", Variables.createVariables().putValue("ok", true));
        completeTask();
    }

    @Test
    @Deployment(resources = "coverage-ratio-test.bpmn")
    public void testCoverageRatio_NotOk() {
        rule.getRuntimeService().startProcessInstanceByKey("testCoverageRatio", Variables.createVariables().putValue("ok", false));
        completeTask();
    }

}

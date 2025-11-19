![build status](https://github.com/operaton/operaton/actions/workflows/build.yml/badge.svg?branch=main)
[![Maven Central Version](https://img.shields.io/maven-central/v/org.operaton.bpm/operaton-bom-root?color=blue&logo=apachemaven)](https://central.sonatype.com/search?q=org.operaton)

[![Forum](https://img.shields.io/badge/forum-Operaton-green)](https://forum.operaton.org/)
[![Slack](https://img.shields.io/badge/chat-Slack-purple)](https://join.slack.com/t/operaton/shared_invite/zt-3id7iv5lz-zT7uGVWLCVNG_zpnAGpq9g)


# Oepraton Process Test Coverage

This Operaton community extension **visualises** test process **paths** and **checks** your process model **coverage** ratio. Running  typical JUnit tests now leaves **html** files in your build output. Just open one and check yourself what your test did:

![Coverage report](docs/assets/img/flowcov_coverage_report.png)

It is forked from the Camunda community extension: https://github.com/camunda-community-hub/camunda-process-test-coverage

## Highlights

* **Visually verify** the paths covered by individual tests **methods** and whole test **classes**
* Visually check gateway **expressions** and transaction borders (**save points**) used by your process
* Calculate and **verify** the nodes (_and_ sequence flow) **coverage** ratio reached by tests methods and classes.

## Just use it

* Integrates with all versions of Operaton
* Tested with JDKs 17 and 21 and different operating systems (Windows, Mac and Linux).
* Supports **JUnit 5** for Operaton
* Can be used inside Spring Tests

## Documentation

If you are interested in further documentation, please check our [Documentation Page](https://camunda-community-hub.github.io/camunda-process-test-coverage/snapshot/index.html)

## Installation

Add a **Maven test dependency** to your project 

### JUnit5

```xml
<dependency>
  <groupId>org.operaton.community.process_test_coverage</groupId>
  <artifactId>operaton-process-test-coverage-junit5-platform-7</artifactId>
  <version>${operaton-process-test-coverage.version}</version>
  <scope>test</scope>
</dependency>
```

## Configuration

Use the **ProcessCoverageInMemProcessEngineConfiguration**, e.g. in your `operaton.cfg.xml`

```xml
<bean id="processEngineConfiguration"
   class="org.operaton.community.process_test_coverage.engine.platform7.ProcessCoverageInMemProcessEngineConfiguration">
   ...
</bean>
```

Use the **ProcessEngineCoverageExtension** as your process engine JUnit extension

 `@RegisterExtension`

If you register the extension on a non-static field, no class coverage and therefore no report will be generated. This is due to the fact, that an instance of the extension will be created per test method.

The extension provides a Builder for programmatic creation, which takes either a path to a configuration resource, a process engine configuration or if nothing is passed uses the default configuration resources path (`operaton.cfg.xml`).

The process engine configuration needs to be configured for test coverage. So use **either** the provided `ProcessCoverageInMemProcessEngineConfiguration`, `SpringProcessWithCoverageEngineConfiguration` or initialize the configuration with `ProcessCoverageConfigurator.initializeProcessCoverageExtensions(configuration)`.

If you use Java:
```java
@RegisterExtension
static ProcessEngineCoverageExtension extension = ProcessEngineCoverageExtension
        .builder().assertClassCoverageAtLeast(0.9).build();
```

If you prefer Kotlin:
```kotlin
companion object {
    @JvmField
    @RegisterExtension
    var extension: ProcessEngineCoverageExtension = ProcessEngineCoverageExtension
            .builder(ProcessCoverageInMemProcessEngineConfiguration())
            .assertClassCoverageAtLeast(1.0).build()
}
```

## Running the tests

Running your JUnit tests now leaves **html** files for individual test methods as well as whole test classes in your project's `target/process-test-coverage` folder. Just open one, check yourself - and have fun with your process tests! :smile:


## News and Noteworthy & Contributors

There are plenty of contributors to this project. Its initial design has been created by the WDW eLab GmbH and some others,
but then the project has been abandoned for some time and received a full rewrite including the new architecture by members
of flowcov.io squad and BPM craftsmen from Holisticon AG. We appreciate any help and effort you put into maintenance
discussion and further development.

Please check the release notes of [individual releases](https://github.com/camunda-community-hub/camunda-process-test-coverage/releases) for the changes and involved contributors.

## License
[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0). See [LICENSE](LICENSE.md) file.

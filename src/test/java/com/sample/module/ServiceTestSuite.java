package com.sample.module;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.sample.module.service.UserServiceTest;

@RunWith(Suite.class)
@SuiteClasses({UserServiceTest.class})
public class ServiceTestSuite {

}

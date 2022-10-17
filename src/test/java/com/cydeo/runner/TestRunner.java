package com.cydeo.runner;

import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("Smoke Test")
//@SelectPackages("com.cydeo.tests.P01_verifications")   // just to run day8 package
//@SelectPackages( {"com.cydeo.tests.day8" , "com.cydeo.tests.day1" }  ) // day1 and day 8
//@SelectClasses( BaseAuthTest.class )  // I just want to run BaseAuthTest

@SelectPackages("com.cydeo.tests") // start looking from this location
//@IncludeTags({ "newsAPI", "contains" })  // look for anything that tagged with smoke 1
//@IncludeTags("db")  // this is class level tag

@ExcludeTags("spartan")  // it will exclude this tag

// MAKE SURE THE TEST CLASSES YOU SELECT HAVE FOLLOWED NAMING CONVENTION
// ClassName should be SomethingTest
// MethodName should be testSomething
public class TestRunner {

}

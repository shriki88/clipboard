# clipboard

Clip Board – Assignment
I have a created a maven project with the below folder structure:
1.	Allure-results – Consists of the results files and allure properties file.
2.	Application Page Classes package under src>>main>>java – Consists of the page classes of the AUT.
3.	CommonUtilities package under src>>main>>java – Consists of the following classes:

    CommonMethods - Used throughout the application
    GettersAndSetters – Used to get the value and set the value at run time.
    Listeners – Used to perform the corresponding actions on the basis of script execution.
    TestConfigurations – Used to Creates and load the extended properties from the specified file

4.	Test.properties file under resources – Consists of key value pairs used throughout the application
5.	E2ETEst class under src>>test>>Java>>ApplicationTests package - It is the End-to-End test’s class with all the application tests.
6.	Server directory under src>>test – It consists the browser drivers, standalone server jar file and a bat file to execute the standalone server programmatically.
7.	SuiteFile directory under src>>test – It consists of the xml suite files to execute it from CLI.

Steps to execute tests:
1.	You can right click on the E2ETest class file and run.
2.	You can click on the green arrow against the required test and run
3.	You can right click on the clipboard.xml file and run as well
4.	You can run through command line as well by keying in the below command on your CLI
    mvn test -DtestngXmlFile=clipboard.xml -Dbrowser=chrome -Dplatform=GRID
5.	Post execution the result will displayed as part of Allure Reports - This has been handled programatically. 
    alternatively command line command - allure serve allure-results can be used from user project root directory
 
 








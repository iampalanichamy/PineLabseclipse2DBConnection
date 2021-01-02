set projectLocation=C:\Users\aman.shukla\eclipse-workspace\TestAutomation

cd %projectLocation%

set classpath=%projectLocation%\bin;%projectLocation%\lib\*

java -cp bin;jars/* org.testng.TestNG testng.xml

pause
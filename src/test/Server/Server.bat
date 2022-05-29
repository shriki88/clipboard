set P=%cd%\src\test\Server

set DIR=C:\ClipBoard\src\test\Server	  	   
cd %DIR%

java -Dwebdriver.chrome.driver="%P%\chromedriver.exe" -jar %P%\selenium-server-4.1.4.jar standalone

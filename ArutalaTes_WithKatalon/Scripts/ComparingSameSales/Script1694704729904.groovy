import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.sql.ResultSet as SqlResultSet
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import com.kms.katalon.core.annotation.Keyword
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('https://www.tokopedia.com/')

WebUI.navigateToUrl('https://tokopedia.com/trasaction_id')

def frontEndElement = findTestObject('Page_Transaction/name=sellerName')

def expectedText = 'FURHAN'

WebUI.verifyElementText(frontEndElement, expectedText, FailureHandling.CONTINUE_ON_FAILURE)

def db = DatabaseFactory.getDatabase('DB_Tokopedia')

def query = 'SELECT SELLER_NAME FROM transaction WHERE TRX_ID = \'01023A9AC\''

SqlResultSet result = CustomKeywords.'com.kms.katalon.core.database.custom.CusDatabaseKeywords.executeQuery'(db, query)

def layananPengirimanDatabase = hasilQuery.getRow(1).getString('SELLER_NAME')

if (expectedText.equals(layananPengirimanDatabase)) {
	WebUI.comment('Layanan pengiriman sesuai dengan di Database')
} else {
	WebUI.comment("Layanan Pengiriman tidak sesuai sesuai dengan di Database. Front End: $expectedText, Database: $layananPengirimanDatabase")
}

WebUI.closeBrowser()

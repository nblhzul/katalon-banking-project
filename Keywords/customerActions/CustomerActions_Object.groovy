package customerActions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class CustomerActions_Object {

	//Buttons
	def btn_customer_login() {

		TestObject btn_customer_login = findTestObject('Login Page/btn_customer_login')
	}
	
	def btn_deposit() {
		
		TestObject btn_deposit = findTestObject('Transactions Page/btn_deposit')
	}
	
	def btn_withdrawal() {
		
		TestObject btn_withdrawal = findTestObject('Transactions Page/btn_withdrawal')
	}

	//Dropdowns
	def dropdown_name() {

		TestObject dropdown_name = findTestObject('Login Page/dropdown_name')
	}

	def dropdown_account_number() {

		TestObject dropdown_account_number = findTestObject('Transactions Page/dropdown_account_number')
	}

	//Inputs
	def input_amount() {
		
		TestObject input_amount = findTestObject('Transactions Page/input_amount')
	}
	
	//Labels
	def label_welcome_customer() {

		TestObject label_welcome_customer = findTestObject('Transactions Page/label_welcome_customer')
	}

	def label_balance() {

		TestObject label_balance = findTestObject('Transactions Page/label_balance')
	}
	
	def label_by_text(String text) {
		
		TestObject option_by_text = findTestObject('Login Page/option_by_text', [('text') : text])
	}

	//Options
	def option_by_text(String text) {

		TestObject option_by_text = findTestObject('Login Page/option_by_text', [('text') : text])
	}
}

package managerActions

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

public class ManagerActions_Object {

	//Buttons
	def btn_manager_login() {

		TestObject btn_manager_login = findTestObject('Login Page/btn_manager_login')
	}

	def btn_add_customer() {

		TestObject btn_add_customer = findTestObject('Login Page/btn_add_customer')
	}

	def btn_home() {

		TestObject btn_home = findTestObject('Login Page/btn_home')
	}

	def btn_submit() {

		TestObject btn_submit = findTestObject('Add Customer Page/btn_submit')
	}

	def btn_customers() {

		TestObject btn_customers = findTestObject('Add Customer Page/btn_customers')
	}

	def btn_delete(def index) {

		TestObject btn_delete = findTestObject('Customers Page/btn_delete', [('index') : index])
	}

	//Inputs
	def input_firstname() {

		TestObject input_firstname = findTestObject('Add Customer Page/input_firstname')
	}

	def input_lastname() {

		TestObject input_lastname = findTestObject('Add Customer Page/input_lastname')
	}

	def input_postcode() {

		TestObject input_postcode = findTestObject('Add Customer Page/input_postcode')
	}

	//Labels

	def label_first_name(def index) {

		TestObject label_first_name = findTestObject('Customers Page/label_first_name', [('index') : index])
	}

	def label_last_name(def index) {

		TestObject label_last_name = findTestObject('Customers Page/label_last_name', [('index') : index])
	}

	def label_postcode(def index) {

		TestObject label_postcode = findTestObject('Customers Page/label_postcode', [('index') : index])
	}
}

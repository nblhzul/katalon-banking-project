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
import com.kms.katalon.core.testdata.InternalData
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class ManagerActions {

	ManagerActions_Object object = new ManagerActions_Object()
	InternalData customerData

	def loginManager() {

		WebUI.openBrowser("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login")
		WebUI.click(object.btn_manager_login())
	}

	@Keyword
	def createCustomer() {

		loginManager()
		WebUI.click(object.btn_add_customer())
		addNewCustomer()
	}

	def addNewCustomer() {

		def firstName, lastName, postcode

		customerData = findTestData('NewCustomers')

		//Import data from internal database into the inputs
		for (int row = 1; row <= customerData.getRowNumbers(); row++){

			firstName = customerData.getObjectValue("firstname", row)
			lastName = customerData.getObjectValue("lastname", row)
			postcode = customerData.getObjectValue("postcode", row)

			WebUI.setText(object.input_firstname(), firstName)
			WebUI.setText(object.input_lastname(), lastName)
			WebUI.setText(object.input_postcode(), postcode)
			WebUI.click(object.btn_submit())
		}
	}

	@Keyword
	def validateAddedCustomers() {

		def firstName, lastName, postcode
		def customerTable_firstName, customerTable_lastName, customerTable_postcode
		customerData = findTestData('NewCustomers')
		def index = 5 //set index to 5 because there are 5 pre-existing data set

		WebUI.click(object.btn_customers())
		WebUI.delay(5)

		for (int row = 1; row <= customerData.getRowNumbers(); row++){

			index++
			firstName = customerData.getObjectValue("firstname", row)
			lastName = customerData.getObjectValue("lastname", row)
			postcode = customerData.getObjectValue("postcode", row)
			customerTable_firstName = WebUI.getText(object.label_first_name(index))
			customerTable_lastName = WebUI.getText(object.label_last_name(index))
			customerTable_postcode = WebUI.getText(object.label_postcode(index))

			WebUI.verifyMatch(customerTable_firstName, firstName, false)
			WebUI.verifyMatch(customerTable_lastName, lastName, false)
			WebUI.verifyMatch(customerTable_postcode, postcode, false)
		}
	}

	@Keyword
	def deleteCustomer() {

		def customer1 = [
			'Jackson',
			'Frank',
			'L789C349'
		]
		def customer2 = [
			'Christopher',
			'Connely',
			'L789C349'
		]

		deleteFunction(customer1)
		deleteFunction(customer2)
	}

	def deleteFunction(def customer) {

		customerData = findTestData('NewCustomers')
		def allData = customerData.getAllData()

		for (int row = 0; row < customerData.getRowNumbers(); row++) {

			if (allData[row] == customer) {

				def index = 5
				index = index + row

				WebUI.click(object.btn_delete(index))
				println('\n\n\nSuccessfully deleted ' + allData[row] + '\n\n\n')
			}
		}
	}
}

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
import com.kms.katalon.core.testdata.InternalData
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import managerActions.ManagerActions_Object

public class CustomerActions {

	CustomerActions_Object object = new CustomerActions_Object()
	ManagerActions_Object manager_object = new ManagerActions_Object()
	InternalData customerData

	@Keyword
	def loginCustomer(String criteria) {

		String customerName, accountNumber, welcomeMessage, welcomeCustomer

		WebUI.openBrowser("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login")
		(customerName, accountNumber) = getCustomerData(criteria)
		WebUI.click(object.btn_customer_login())
		WebUI.click(object.dropdown_name())
		WebUI.click(object.option_by_text(customerName))
		WebUI.click(manager_object.btn_submit())

		//Verify user is in expected customer homepage
		welcomeMessage = 'Welcome ' + customerName + ' !!'
		welcomeCustomer = WebUI.getText(object.label_welcome_customer())
		WebUI.verifyMatch(welcomeCustomer, welcomeMessage, false)

	}

	def getCustomerData(String criteria) {

		def customerName, accountNumber
		customerData = findTestData('ExistingCustomers')

		for (int row = 1; row <= customerData.getRowNumbers(); row++){

			customerName = customerData.getObjectValue("fullname", row)
			accountNumber = customerData.getObjectValue("account_number", row)
		}
		return[customerName, accountNumber]
	}

	@Keyword
	def customerTransaction(String criteria) {

		def currentBalance, userCurrentBalance, customerName, accountNumber
		(customerName, accountNumber) = getCustomerData(criteria)

		WebUI.click(object.dropdown_account_number())
		WebUI.click(object.option_by_text(accountNumber))

		//Transaction calculation setup
		currentBalance = 0
		def amount = [
			50000,
			3000,
			2000,
			5000,
			10000,
			15000,
			1500
		]
		def transactionType = [
			'Credit',
			'Debit',
			'Debit',
			'Credit',
			'Debit',
			'Debit',
			'Credit'
		]
		def customerBalance = []

		//Verify initial balance amount is as expected
		userCurrentBalance = WebUI.getText(object.label_balance())
		WebUI.verifyMatch(userCurrentBalance, currentBalance.toString(), false)

		for (int x = 0; x < amount.size; x++) {

			if (transactionType[x] == 'Credit') {

				if(amount[x] > currentBalance) {

					//Try to withdraw with amount larger than user's current balance
					WebUI.click(object.btn_withdrawal())
					WebUI.setText(object.input_amount(), amount[x].toString())
					WebUI.click(manager_object.btn_submit())
					WebUI.verifyElementPresent(object.label_by_text('Transaction Failed'), 0)
					WebUI.click(object.btn_deposit()) //to clear transaction failed error message

					//Set amount to 0 if it's larger than user's current balance
					amount[x] = 0
				}

				//Transaction calculation
				currentBalance = currentBalance - amount[x]
				customerBalance.add(currentBalance)

				//Transaction action
				WebUI.click(object.btn_withdrawal())
				WebUI.setText(object.input_amount(), amount[x].toString())
				WebUI.click(manager_object.btn_submit())

				if(amount[x] != 0) {

					WebUI.verifyElementPresent(object.label_by_text('Transaction successful'), 0)
				}

				//Verify current balance tallies with homepage balance section
				userCurrentBalance = WebUI.getText(object.label_balance())
				WebUI.verifyMatch(userCurrentBalance, currentBalance.toString(), false)
			}

			if (transactionType[x] == 'Debit') {

				//Transaction calculation
				currentBalance = currentBalance + amount[x]
				customerBalance.add(currentBalance)

				//Transaction action
				WebUI.click(object.btn_deposit())
				WebUI.setText(object.input_amount(), amount[x].toString())
				WebUI.click(manager_object.btn_submit())
				WebUI.verifyElementPresent(object.label_by_text('Deposit Successful'), 0)

				//Verify current balance tallies with homepage balance section
				userCurrentBalance = WebUI.getText(object.label_balance())
				WebUI.verifyMatch(userCurrentBalance, currentBalance.toString(), false)
			}
		}

		println('\n\n\nCustomer balance is = ' + customerBalance + '\n\n\n')
	}
}

package uk.ac.standrews.cs.cs2001.w03.test;

import org.junit.Before;
import org.junit.Test;
import uk.ac.standrews.cs.cs2001.w03.common.*;
import uk.ac.standrews.cs.cs2001.w03.interfaces.ICustomer;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachine;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This is a JUnit test class for the Vending Machine ADT.
 */
public class Tests extends AbstractFactoryClient {

    private IVendingMachine vendingMachine;
    private IVendingMachineProduct vendingMachineProduct;

    /**
     * Create a new vending machine object before every test.
     *
     * @throws InvalidProductPriceException if the product is created with a negative price
     */
    @Before
    public void initialize() throws InvalidProductPriceException {
        vendingMachine = getFactory().makeVendingMachine();
        vendingMachineProduct = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps", 1.99);
    }

    /**
     * Test which tests that a vending machine is created and is not null.
     */
    @Test
    public void testVendingMachineCreation() {
        assertNotNull(vendingMachine);
    }

    /**
     * Test which tests that a vending machine product is created and is not null.
     */
    @Test
    public void testVendingMachineProductCreation() {
        assertNotNull(vendingMachineProduct);
    }

    /**
     * Test which tests that a product record is created and is not null.
     */
    @Test
    public void testMakeProductRecord() {
        assertNotNull(getFactory().makeProductRecord(vendingMachineProduct));
    }

    /**
     * Test which tests that a product description is maintained once registered
     *
     * @throws LaneCodeNotRegisteredException if duplicate lanecode exists
     */
    @Test
    public void testVendingMachineProductDescription() throws LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(vendingMachineProduct);
        assertEquals(vendingMachineProduct.getDescription(), vendingMachine.getProductList().get(vendingMachineProduct.getLaneCode()).getProduct().getDescription());
    }

    /**
     * Test which tests that a customer object is created and is not null.
     *
     * @throws AddingInvalidFundsException if the balance at customer creation is negative
     */
    @Test
    public void testCreateCustomer() throws AddingInvalidFundsException {
        assertNotNull(getFactory().makeCustomer("Jon", 300));
    }

    /**
     * Test which tests if a customer's name is kept after they are registered
     *
     * @throws AddingInvalidFundsException if the balance at customer creation is negative
     */
    @Test
    public void testCustomerNameRetention() throws AddingInvalidFundsException {
        ICustomer customer = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerNewCustomer(customer);
        assertEquals(customer.getCustomerName(), vendingMachine.getCustomerList().get(0).getCustomerName());
    }


    /**
     * Test which creates a new product and registers it in the vending machine.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test
    public void testRegisterVendingMachineProduct() throws LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(vendingMachineProduct);
        assertEquals(1, vendingMachine.getNumberOfProducts());
    }

    /**
     * Test which registers the same product twice, test expected to run into runtime exception LaneCodeAlreadyInUse.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test(expected = LaneCodeAlreadyInUseException.class)
    public void testRegisterSameItemTwice() throws LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.registerProduct(vendingMachineProduct);
    }

    /**
     * Test which registers 10 new products to the vending machine.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     * @throws InvalidProductPriceException  if the product is created with a negative price
     * @result Vending machine with 10 new products stored inside
     */
    @Test
    public void testRegisterTenVendingMachineProducts() throws LaneCodeAlreadyInUseException, InvalidProductPriceException {
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct4 = getFactory().makeVendingMachineProduct("A4", "Barbeque Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct5 = getFactory().makeVendingMachineProduct("A5", "Salt & Vinegar Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct6 = getFactory().makeVendingMachineProduct("B1", "Cheese & Onion Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct7 = getFactory().makeVendingMachineProduct("B2", "Beef Onion Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct8 = getFactory().makeVendingMachineProduct("B3", "Roast Chicken Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct9 = getFactory().makeVendingMachineProduct("B4", "Smoked Bacon Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct10 = getFactory().makeVendingMachineProduct("B5", "Sour Cream & Onion Crisps", 1.99);
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.registerProduct(vendingMachineProduct2);
        vendingMachine.registerProduct(vendingMachineProduct3);
        vendingMachine.registerProduct(vendingMachineProduct4);
        vendingMachine.registerProduct(vendingMachineProduct5);
        vendingMachine.registerProduct(vendingMachineProduct6);
        vendingMachine.registerProduct(vendingMachineProduct7);
        vendingMachine.registerProduct(vendingMachineProduct8);
        vendingMachine.registerProduct(vendingMachineProduct9);
        vendingMachine.registerProduct(vendingMachineProduct10);
        assertEquals(10, vendingMachine.getNumberOfProducts());
    }

    /**
     * Test which registers products with duplicate lane code, test is expected to run into runtime exception LaneCodeAlreadyInUseException.
     *
     * @throws InvalidProductPriceException  if the product is created with a negative price
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test(expected = LaneCodeAlreadyInUseException.class)
    public void testRegisterItemWithDuplicateLaneCode() throws LaneCodeAlreadyInUseException, InvalidProductPriceException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A1", "Tomato Ketchup Crisps", 1.99);
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
    }

    /**
     * Test which registers products with a duplicate description.
     *
     * @throws InvalidProductPriceException  if the product is created with a negative price
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test
    public void testRegisteringDuplicateDescriptions() throws LaneCodeAlreadyInUseException, InvalidProductPriceException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Haggis Crisps", 1.99);
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
        assertEquals(2, vendingMachine.getNumberOfProducts());
    }

    /**
     * Test which registers and then unregisters a product in the vending machine.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test
    public void testUnregisterProduct() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.unregisterProduct(vendingMachineProduct);
        assertEquals(0, vendingMachine.getNumberOfProducts());
    }

    /**
     * Test which will throw an exception since the product is not registered in the vending machine.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testUnregisteringNonExistentProduct() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.unregisterProduct(vendingMachineProduct);
    }

    /**
     * Test which will register multiple items and then unregister them.
     *
     * @throws InvalidProductPriceException   if the product is created with a negative price
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test
    public void testUnregisteringMultipleProducts() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, InvalidProductPriceException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct4 = getFactory().makeVendingMachineProduct("A4", "Barbeque Crisps", 1.99);
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
        vendingMachine.registerProduct(vendingMachineProduct3);
        vendingMachine.registerProduct(vendingMachineProduct4);
        vendingMachine.unregisterProduct(vendingMachineProduct1);
        vendingMachine.unregisterProduct(vendingMachineProduct2);
        vendingMachine.unregisterProduct(vendingMachineProduct3);
        vendingMachine.unregisterProduct(vendingMachineProduct4);
        assertEquals(0, vendingMachine.getNumberOfProducts());
    }

    /**
     * Tests which registers one item in the vending machine and adds it into the vending machine.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test
    public void testAddOneItemToStock() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        assertEquals(1, vendingMachine.getTotalNumberOfItems());
    }

    /**
     * Test which registers one product and adds 10 of it to stock.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test
    public void testAddMultipleSingleProductToStock() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(vendingMachineProduct);
        for (int i = 0; i < 10; i++) {
            vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        }
        assertEquals(10, vendingMachine.getTotalNumberOfItems());
    }

    /**
     * Tests which registers multuple items in the vending machine and adds them into the vending machine.
     *
     * @throws InvalidProductPriceException   if the product is created with a negative price
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test
    public void testAddMultipleItemsToStock() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, InvalidProductPriceException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct4 = getFactory().makeVendingMachineProduct("A4", "Barbeque Crisps", 1.99);
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
        vendingMachine.registerProduct(vendingMachineProduct3);
        vendingMachine.registerProduct(vendingMachineProduct4);
        vendingMachine.addItem(vendingMachineProduct1.getLaneCode());
        vendingMachine.addItem(vendingMachineProduct2.getLaneCode());
        vendingMachine.addItem(vendingMachineProduct3.getLaneCode());
        vendingMachine.addItem(vendingMachineProduct4.getLaneCode());
        assertEquals(4, vendingMachine.getTotalNumberOfItems());
    }

    /**
     * Tests which adds an unregistered product to the stock level, test expected to run into runtime exception LaneCodeNotRegisteredException.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testAddUnregisteredProduct() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
    }

    /**
     * Test which finds the total number of items for one particular item and not the entire vending machine.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws InvalidProductPriceException   if the product is created with a negative price
     */
    @Test
    public void testGetNumberOfOneItem() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, InvalidProductPriceException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps", 1.99);
        vendingMachine.registerProduct(vendingMachineProduct1);
        for (int i = 0; i < 10; i++) {
            vendingMachine.addItem(vendingMachineProduct1.getLaneCode());
        }
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Haggis Crisps", 1.99);
        vendingMachine.registerProduct(vendingMachineProduct2);
        vendingMachine.addItem(vendingMachineProduct2.getLaneCode());
        assertEquals(10, vendingMachine.getNumberOfItems(vendingMachineProduct1.getLaneCode()));
    }

    /**
     * Test which tries to find the number of items a lanecode holds when the product is unregistered, test expected to run into runtime exception LaneCodeNotRegisteredException.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testUnregisteredProductNumberOfItems() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        assertEquals(0, vendingMachine.getNumberOfItems(vendingMachineProduct.getLaneCode()));
    }

    /**
     * Test which creates a product with negative price, test expected to run into runtime exception ProductUnavailableException
     *
     * @throws InvalidProductPriceException if the product is created with a negative price
     */
    @Test(expected = InvalidProductPriceException.class)
    public void testCreateItemWithNegativePrice() throws InvalidProductPriceException {
        IVendingMachineProduct vendingMachineProduct = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps", -1.99);
    }


    /**
     * Test which registers 1 customer.
     *
     * @throws AddingInvalidFundsException if the customer at object creation is negative
     */
    @Test
    public void testRegisterCustomer() throws AddingInvalidFundsException {
        ICustomer customer = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerNewCustomer(customer);
        assertEquals(1, vendingMachine.getNumberOfCustomers());
    }

    /**
     * Test which registers 10 customer.
     *
     * @throws AddingInvalidFundsException if the customer at object creation is negative
     */
    @Test
    public void testRegisterTenCustomers() throws AddingInvalidFundsException {
        ICustomer customer1 = getFactory().makeCustomer("Jon", 300);
        ICustomer customer2 = getFactory().makeCustomer("Alex", 300);
        ICustomer customer3 = getFactory().makeCustomer("Simon", 300);
        ICustomer customer4 = getFactory().makeCustomer("Steve", 300);
        ICustomer customer5 = getFactory().makeCustomer("Tristan", 300);
        ICustomer customer6 = getFactory().makeCustomer("Lei Fang", 300);
        ICustomer customer7 = getFactory().makeCustomer("Daniel", 300);
        ICustomer customer8 = getFactory().makeCustomer("Adam", 300);
        ICustomer customer9 = getFactory().makeCustomer("Aaron", 300);
        ICustomer customer10 = getFactory().makeCustomer("Greg", 300);

        vendingMachine.registerNewCustomer(customer1);
        vendingMachine.registerNewCustomer(customer2);
        vendingMachine.registerNewCustomer(customer3);
        vendingMachine.registerNewCustomer(customer4);
        vendingMachine.registerNewCustomer(customer5);
        vendingMachine.registerNewCustomer(customer6);
        vendingMachine.registerNewCustomer(customer7);
        vendingMachine.registerNewCustomer(customer8);
        vendingMachine.registerNewCustomer(customer9);
        vendingMachine.registerNewCustomer(customer10);

        assertEquals(10, vendingMachine.getNumberOfCustomers());
    }


    /**
     * Test which registers a null customer object, test expected to fail since you cannot add a null customer.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterNullCustomer() {
        ICustomer customer = null;
        vendingMachine.registerNewCustomer(customer);
        assertEquals(0, vendingMachine.getNumberOfCustomers());
    }

    /**
     * Test which registers a customer with no name.
     *
     * @throws AddingInvalidFundsException if the balance at customer creation is negative
     */
    @Test
    public void testRegisterCustomerWithNoName() throws AddingInvalidFundsException {
        ICustomer customer = getFactory().makeCustomer("", 300);
        vendingMachine.registerNewCustomer(customer);
        assertEquals(1, vendingMachine.getNumberOfCustomers());
    }

    /**
     * Test which registers two customers with the same name.
     *
     * @throws AddingInvalidFundsException if the balance at customer creation is negative
     */
    @Test
    public void testRegisterCustomersWithSameName() throws AddingInvalidFundsException {
        ICustomer customer1 = getFactory().makeCustomer("Jon", 300);
        ICustomer customer2 = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerNewCustomer(customer1);
        vendingMachine.registerNewCustomer(customer2);
        assertEquals(2, vendingMachine.getNumberOfCustomers());
    }

    /**
     * Test which registers a customer with 0 balance.
     *
     * @throws AddingInvalidFundsException if the balance at customer creation is negative
     */
    @Test
    public void testRegisterCustomerWithNoBalance() throws AddingInvalidFundsException {
        ICustomer customer = getFactory().makeCustomer("Jon", 0);
        vendingMachine.registerNewCustomer(customer);
        assertEquals(1, vendingMachine.getNumberOfCustomers());
    }

    /**
     * Test which registers a customer with no name and 0 balance.
     *
     * @throws AddingInvalidFundsException if the balance at customer creation is negative
     */
    @Test
    public void testRegisterCustomerWithNoNameAndNoBalance() throws AddingInvalidFundsException {
        ICustomer customer = getFactory().makeCustomer("", 0);
        vendingMachine.registerNewCustomer(customer);
        assertEquals(1, vendingMachine.getNumberOfCustomers());
    }

    /**
     * Test which registers a customer with negative balance, test expected to run into runtime exception AddingInvalidFundsException.
     *
     * @throws AddingInvalidFundsException if the balance at customer creation is negative
     */
    @Test(expected = AddingInvalidFundsException.class)
    public void testRegisterCustomerWithNegativeBalance() throws AddingInvalidFundsException {
        ICustomer customer = getFactory().makeCustomer("", -10);
        vendingMachine.registerNewCustomer(customer);
        assertEquals(1, vendingMachine.getNumberOfCustomers());
    }

    /**
     * Test which unregisters a customer.
     *
     * @throws AddingInvalidFundsException  if the balance at customer creation is negative
     * @throws CustomerDoesntExistException if the customer is not registered with the vending machine
     */
    @Test
    public void testUnregisterCustomer() throws AddingInvalidFundsException, CustomerDoesntExistException {
        ICustomer customer = getFactory().makeCustomer("Jon", 10);
        vendingMachine.registerNewCustomer(customer);
        vendingMachine.unregisterCustomer(customer);
        assertEquals(0, vendingMachine.getNumberOfCustomers());
    }

    /**
     * Test which unregisters a non registered customer, test is expected to fail and run into runtime exception CustomerDoesntExistException.
     *
     * @throws AddingInvalidFundsException  if the balance at customer creation is negative
     * @throws CustomerDoesntExistException if the customer is not registered with the vending machine
     */
    @Test(expected = CustomerDoesntExistException.class)
    public void testUnregisterNonExistentCustomer() throws AddingInvalidFundsException, CustomerDoesntExistException {
        vendingMachine.unregisterCustomer(getFactory().makeCustomer("Jon", 100));
    }

    /**
     * Test which unregisters a null customer object.
     *
     * @throws CustomerDoesntExistException if the customer is not registered with the vending machine
     */
    @Test(expected = CustomerDoesntExistException.class)
    public void testUnregisterNullCustomer() throws CustomerDoesntExistException {
        vendingMachine.unregisterCustomer(null);
    }

    /**
     * Test which adds money to the customer balance.
     *
     * @throws AddingInvalidFundsException if the amount the customer is adding is negative
     */
    @Test
    public void testAddMoney() throws AddingInvalidFundsException {
        ICustomer customer = getFactory().makeCustomer("Jon", 10);
        customer.addMoney(10.0);
        assertEquals(20.0, customer.getCustomerBalance(), 0.00);
    }

    /**
     * Test which adds 0 to the customer balance.
     *
     * @throws AddingInvalidFundsException if the amount the customer is adding is negative
     */
    @Test
    public void testAddZeroToBalance() throws AddingInvalidFundsException {
        ICustomer customer = getFactory().makeCustomer("Jon", 10);
        customer.addMoney(0.0);
        assertEquals(10.0, customer.getCustomerBalance(), 0.00);
    }

    /**
     * Test which adds null to customer balance.
     *
     * @throws AddingInvalidFundsException
     */
    @Test(expected = AddingInvalidFundsException.class)
    public void testAddNullToBalance() throws AddingInvalidFundsException {
        Double nullAmount = null;
        ICustomer customer = getFactory().makeCustomer("Jon", 10);
        customer.addMoney(nullAmount);
    }

    /**
     * Test which adds negative amount to the customer balance.
     *
     * @throws AddingInvalidFundsException if the amount the customer is adding is negative
     */
    @Test(expected = AddingInvalidFundsException.class)
    public void testAddNegativeNumberToBalance() throws AddingInvalidFundsException {
        ICustomer customer = getFactory().makeCustomer("Jon", 10);
        customer.addMoney(-10.0);
        assertEquals(10.0, customer.getCustomerBalance(), 0.00);
    }

    /**
     * Test which reduces the balance of the customer.
     *
     * @throws AddingInvalidFundsException if the amount the customer is adding is negative
     * @throws BalanceTooLowException      if the customer balance after the purchase will go into negative value
     */
    @Test
    public void testReduceMoney() throws AddingInvalidFundsException, BalanceTooLowException {
        ICustomer customer = getFactory().makeCustomer("Jon", 10);
        customer.reduceBalance(6.0);
        assertEquals(4.0, customer.getCustomerBalance(), 0.00);
    }

    /**
     * Test which reduces the balance of the customer by a negative amount.
     *
     * @throws AddingInvalidFundsException if the amount the customer is adding is negative
     * @throws BalanceTooLowException      if the customer balance after the purchase will go into negative value
     */
    @Test(expected = AddingInvalidFundsException.class)
    public void testReduceNegativeMoney() throws AddingInvalidFundsException, BalanceTooLowException {
        ICustomer customer = getFactory().makeCustomer("Jon", 10);
        customer.reduceBalance(-6.0);
    }

    /**
     * Test which reduces the balance of the customer by 0.
     *
     * @throws AddingInvalidFundsException if the amount the customer is adding is negative
     * @throws BalanceTooLowException      if the customer balance after the purchase will go into negative value
     */
    @Test
    public void testReduceZeroMoney() throws AddingInvalidFundsException, BalanceTooLowException {
        ICustomer customer = getFactory().makeCustomer("Jon", 10);
        customer.reduceBalance(-0.0);
        assertEquals(10, customer.getCustomerBalance(), 0.00);
    }

    /**
     * Test which reduces the balance of the customer by a null amount.
     *
     * @throws AddingInvalidFundsException if the amount the customer is adding is negative
     * @throws BalanceTooLowException      if the customer balance after the purchase will go into negative value
     */
    @Test(expected = AddingInvalidFundsException.class)
    public void testReduceNullMoney() throws AddingInvalidFundsException, BalanceTooLowException {
        ICustomer customer = getFactory().makeCustomer("Jon", 10);
        customer.reduceBalance(null);
    }

    /**
     * Test which adds a product to the customer's basket.
     *
     * @throws ProductUnavailableException    if there is not enough stock to purchase the item
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws AddingInvalidFundsException    if the balance at object creation is negative
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     */
    @Test
    public void testAddItemToBasketRegisteredCustomer() throws ProductUnavailableException, LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException, AddingInvalidFundsException, CustomerDoesntExistException {
        ICustomer customer = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerNewCustomer(customer);
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        vendingMachine.addItemToBasket(vendingMachineProduct, customer);
        assertEquals(1, customer.getCustomerBasket().size());
        assertEquals(0, vendingMachine.getNumberOfItems(vendingMachineProduct.getLaneCode()));
    }

    /**
     * Test which adds item to a non registered customer's basket.
     *
     * @throws AddingInvalidFundsException    if the balance at object creation is negative
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     * @throws ProductUnavailableException    if there is not enough stock to purchase the item
     */
    @Test(expected = CustomerDoesntExistException.class)
    public void testAddItemToBasketNonRegisteredCustomer() throws AddingInvalidFundsException, LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, CustomerDoesntExistException, ProductUnavailableException {
        ICustomer customer = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        vendingMachine.addItemToBasket(vendingMachineProduct, customer);
    }


    /**
     * Test which adds four items to basket.
     *
     * @throws InvalidProductPriceException   if product has a negative price
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     * @throws ProductUnavailableException    if there is not enough stock to purchase the item
     * @throws AddingInvalidFundsException    if the balance at object creation is negative
     */
    @Test
    public void testAddFourItemsToBasket() throws InvalidProductPriceException, LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, CustomerDoesntExistException, ProductUnavailableException, AddingInvalidFundsException {
        ICustomer customer = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerNewCustomer(customer);

        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct4 = getFactory().makeVendingMachineProduct("A4", "Barbeque Crisps", 1.99);

        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
        vendingMachine.registerProduct(vendingMachineProduct3);
        vendingMachine.registerProduct(vendingMachineProduct4);

        vendingMachine.addItem(vendingMachineProduct1.getLaneCode());
        vendingMachine.addItem(vendingMachineProduct2.getLaneCode());
        vendingMachine.addItem(vendingMachineProduct3.getLaneCode());
        vendingMachine.addItem(vendingMachineProduct4.getLaneCode());

        vendingMachine.addItemToBasket(vendingMachineProduct1, customer);
        vendingMachine.addItemToBasket(vendingMachineProduct2, customer);
        vendingMachine.addItemToBasket(vendingMachineProduct3, customer);
        vendingMachine.addItemToBasket(vendingMachineProduct4, customer);

        assertEquals(4, customer.getCustomerBasket().size());
        assertEquals(1.99 * 4, customer.getTotalBasketCost(), 00);
    }


    /**
     * Test which adds a null item to a customer's basket, test expected to run into runtime error LaneCodeNotRegisteredException.
     *
     * @throws AddingInvalidFundsException    if the balance at object creation is negative
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if there is not enough stock to purchase the item
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testAddNullItemToBasket() throws AddingInvalidFundsException, CustomerDoesntExistException, LaneCodeNotRegisteredException, ProductUnavailableException {
        ICustomer customer = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerNewCustomer(customer);
        vendingMachine.addItemToBasket(null, customer);
    }

    /**
     * Test which adds an item to a null customer's basket, test expected to run into runtime error CustomerDoesntExistException.
     *
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if there is not enough stock to purchase the item
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     */
    @Test(expected = CustomerDoesntExistException.class)
    public void testAddItemToNullCustomerBasket() throws CustomerDoesntExistException, LaneCodeNotRegisteredException, ProductUnavailableException, LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        vendingMachine.addItemToBasket(vendingMachineProduct, null);
    }

    /**
     * Test which adds registered item with 0 stock to basket, test expected to run into runtime error ProductUnavailableException.
     *
     * @throws AddingInvalidFundsException    if the balance at object creation is negative
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if there is not enough stock to purchase the item
     */
    @Test(expected = ProductUnavailableException.class)
    public void testAddItemToBasketWhenThereIsZeroStock() throws AddingInvalidFundsException, LaneCodeAlreadyInUseException, CustomerDoesntExistException, LaneCodeNotRegisteredException, ProductUnavailableException {
        ICustomer customer = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerNewCustomer(customer);
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItemToBasket(vendingMachineProduct, customer);
    }

    /**
     * Test which purchases a basket with 1 item.
     *
     * @throws AddingInvalidFundsException    if the balance at object creation is negative
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     * @throws ProductUnavailableException    if there is not enough stock to purchase the item
     * @throws BalanceTooLowException         if the customer balance is too low to purchase the basket
     */
    @Test
    public void testPurchaseBasket() throws AddingInvalidFundsException, LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, CustomerDoesntExistException, ProductUnavailableException, BalanceTooLowException {
        ICustomer customer = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerNewCustomer(customer);
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        vendingMachine.addItemToBasket(vendingMachineProduct, customer);
        vendingMachine.buyBasket(customer);

        assertEquals(300 - vendingMachineProduct.getPrice(), customer.getCustomerBalance(), 0.00);
    }

    /**
     * Test which purchases a basket with 0 items.
     *
     * @throws AddingInvalidFundsException    if the balance at object creation is negative
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws BalanceTooLowException         if the customer balance is too low to purchase the basket
     */
    @Test
    public void testPurchaseEmptyBasket() throws AddingInvalidFundsException, CustomerDoesntExistException, LaneCodeNotRegisteredException, BalanceTooLowException {
        ICustomer customer = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerNewCustomer(customer);
        vendingMachine.buyBasket(customer);

        assertEquals(300, customer.getCustomerBalance(), 0.00);
    }

    /**
     * Test which purchases a basket with not enough balance, test expected to run into runtime exception BalanceTooLowException.
     *
     * @throws AddingInvalidFundsException    if the balance at object creation is negative
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     * @throws ProductUnavailableException    if there is not enough stock to purchase the item
     * @throws BalanceTooLowException         if the customer balance is too low to purchase the basket
     */
    @Test(expected = BalanceTooLowException.class)
    public void testPurchaseWithNotEnoughBalance() throws AddingInvalidFundsException, LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, CustomerDoesntExistException, ProductUnavailableException, BalanceTooLowException {
        ICustomer customer = getFactory().makeCustomer("Jon", 1);
        double balance = customer.getCustomerBalance();
        vendingMachine.registerNewCustomer(customer);
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        vendingMachine.addItemToBasket(vendingMachineProduct, customer);
        vendingMachine.buyBasket(customer);
        assertEquals(balance, customer.getCustomerBalance(), 0.00);
    }

    /**
     * Test which buys the basket twice.
     *
     * @throws AddingInvalidFundsException    if the balance at object creation is negative
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     * @throws ProductUnavailableException    if there is not enough stock to purchase the item
     * @throws BalanceTooLowException         if the customer balance is too low to purchase the basket
     */
    @Test
    public void testPurchaseBasketTwice() throws AddingInvalidFundsException, LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, CustomerDoesntExistException, ProductUnavailableException, BalanceTooLowException {
        ICustomer customer = getFactory().makeCustomer("Jon", 10);
        vendingMachine.registerNewCustomer(customer);
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        vendingMachine.addItemToBasket(vendingMachineProduct, customer);
        vendingMachine.buyBasket(customer);
        double balance = customer.getCustomerBalance();
        vendingMachine.buyBasket(customer);
        assertEquals(balance, customer.getCustomerBalance(), 0.00);
    }

    /**
     * Test how many times a product invalid lanecode was bought, test is expected to run into runtime exception LaneCodeNotRegistered.
     *
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testNumberOfTimesNonExistentLaneCodeWasBought() throws LaneCodeNotRegisteredException {
        vendingMachine.getNumberOfSales("A2");
    }

    /**
     * Registering a product with a null lane code, test expected to run into runtime exception NullPointerException.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     * @throws InvalidProductPriceException  if the product is created with a negative price
     */
    @Test(expected = NullPointerException.class)
    public void testNullLaneCode() throws LaneCodeAlreadyInUseException, InvalidProductPriceException {
        vendingMachineProduct = getFactory().makeVendingMachineProduct(null, "Haggis Crisps", 1.99);
        vendingMachine.registerProduct(vendingMachineProduct);
    }

    /**
     * Registering a product with a null description.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     * @throws InvalidProductPriceException  if the product is created with a negative price
     */
    @Test
    public void testNullDescription() throws LaneCodeAlreadyInUseException, InvalidProductPriceException {
        vendingMachineProduct = getFactory().makeVendingMachineProduct("A1", null, 1.99);
        vendingMachine.registerProduct(vendingMachineProduct);
    }

    /**
     * Registering null vending machine product to vending machine, test expected to run into runtime exception error NullPointerException.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterNullProduct() throws LaneCodeAlreadyInUseException {
        vendingMachineProduct = null;
        vendingMachine.registerProduct(vendingMachineProduct);
    }

    /**
     * Unregistering null vending machine product to vending machine, test expected to run into runtime exception error NullPointerException.
     *
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = NullPointerException.class)
    public void testUnregisterNullProduct() throws LaneCodeNotRegisteredException {
        vendingMachineProduct = null;
        vendingMachine.unregisterProduct(vendingMachineProduct);
    }

    /**
     * Test that when no products are registered, there are no products.
     */
    @Test
    public void testGetNumberOfProductsWithZeroProductsRegistered() {
        assertEquals(0, vendingMachine.getNumberOfProducts());
    }

    /**
     * Test that adds an item with lane code of null.
     *
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testAddNullProduct() throws LaneCodeNotRegisteredException {
        vendingMachine.addItem(null);
    }


    /**
     * Test that when no products are added, there are no items.
     */
    @Test
    public void testGetTotalNumberOfItemsWithZeroItemsAdded() {
        assertEquals(0, vendingMachine.getTotalNumberOfItems());
    }

    /**
     * Test which attempts to retrieve the number of items on null lanecode, test is expected to run into runtime exception LaneCodeNotRegisteredException.
     *
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testGetNumberOfItemsWithNullLaneCode() throws LaneCodeNotRegisteredException {
        vendingMachine.getNumberOfItems(null);
    }

    /**
     * Test which attempts to get the number of sales from an item with null lanecode, test is expected to run into runtime exception LaneCodeNotRegisteredException.
     *
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testNumberOfSalesForNullLaneCode() throws LaneCodeNotRegisteredException {
        vendingMachine.getNumberOfSales(null);
    }

    /**
     * Test which attempts to find the most popular item with no products registered, test is expected to run into runtime exception LaneCodeNotRegisteredException.
     *
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testMostPopularItemWithNoRegisteredProducts() throws LaneCodeNotRegisteredException {
        vendingMachine.getMostPopular();
    }

    /**
     * Test which determines the most popular product.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the specified lane is empty
     */
    @Test
    public void testMostPopularProduct() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException, InvalidProductPriceException, AddingInvalidFundsException, CustomerDoesntExistException, BalanceTooLowException {
        ICustomer customer = getFactory().makeCustomer("Jon", 300);
        vendingMachine.registerNewCustomer(customer);

        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps", 1.99);
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps", 1.45);
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps", 1.25);

        //register items
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
        vendingMachine.registerProduct(vendingMachineProduct3);

        //add 10 if each item
        for (int i = 0; i < 10; i++) {
            vendingMachine.addItem(vendingMachineProduct1.getLaneCode());
            vendingMachine.addItem(vendingMachineProduct2.getLaneCode());
            vendingMachine.addItem(vendingMachineProduct3.getLaneCode());
        }

        //add product1 to basket multiple times
        for (int i = 0; i < 4; i++) {
            vendingMachine.addItemToBasket(vendingMachineProduct, customer);

        }

        //purchase the basket
        vendingMachine.buyBasket(customer);

        //determine most popular
        assertEquals(vendingMachineProduct1, vendingMachine.getMostPopular());
    }
}

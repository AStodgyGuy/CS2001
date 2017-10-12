package uk.ac.standrews.cs.cs2001.w03.test;

import org.junit.Before;
import org.junit.Test;
import uk.ac.standrews.cs.cs2001.w03.common.AbstractFactoryClient;
import uk.ac.standrews.cs.cs2001.w03.common.LaneCodeAlreadyInUseException;
import uk.ac.standrews.cs.cs2001.w03.common.LaneCodeNotRegisteredException;
import uk.ac.standrews.cs.cs2001.w03.common.ProductUnavailableException;
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
     */
    @Before
    public void initialize() {
        vendingMachine = getFactory().makeVendingMachine();
        vendingMachineProduct = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
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
     * Test which tests that a product description is maintained once registered.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test
    public void testVendingMachineProductDescription() throws LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(vendingMachineProduct);
        assertEquals("Haggis Crisps", vendingMachineProduct.getDescription());
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
     * @result Vending machine with 10 new products stored inside
     */
    @Test
    public void testRegisterTenVendingMachineProducts() throws LaneCodeAlreadyInUseException {
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps");
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps");
        IVendingMachineProduct vendingMachineProduct4 = getFactory().makeVendingMachineProduct("A4", "Barbeque Crisps");
        IVendingMachineProduct vendingMachineProduct5 = getFactory().makeVendingMachineProduct("A5", "Salt & Vinegar Crisps");
        IVendingMachineProduct vendingMachineProduct6 = getFactory().makeVendingMachineProduct("B1", "Cheese & Onion Crisps");
        IVendingMachineProduct vendingMachineProduct7 = getFactory().makeVendingMachineProduct("B2", "Beef Onion Crisps");
        IVendingMachineProduct vendingMachineProduct8 = getFactory().makeVendingMachineProduct("B3", "Roast Chicken Crisps");
        IVendingMachineProduct vendingMachineProduct9 = getFactory().makeVendingMachineProduct("B4", "Smoked Bacon Crisps");
        IVendingMachineProduct vendingMachineProduct10 = getFactory().makeVendingMachineProduct("B5", "Sour Cream & Onion Crisps");
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
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test(expected = LaneCodeAlreadyInUseException.class)
    public void testRegisterItemWithDuplicateLaneCode() throws LaneCodeAlreadyInUseException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A1", "Tomato Ketchup Crisps");
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
    }

    /**
     * Test which registers products with a duplicate description.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test
    public void testRegisteringDuplicateDescriptions() throws LaneCodeAlreadyInUseException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Haggis Crisps");
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
        assertEquals(2, vendingMachine.getNumberOfProducts());
    }

    /**
     * Test which registers products with a empty lane code.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test
    public void testRegisterEmptyLaneCode() throws LaneCodeAlreadyInUseException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("", "Haggis Crisps");
        vendingMachine.registerProduct(vendingMachineProduct1);
        assertEquals(1, vendingMachine.getNumberOfProducts());
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
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test
    public void testUnregisteringMultipleProducts() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps");
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps");
        IVendingMachineProduct vendingMachineProduct4 = getFactory().makeVendingMachineProduct("A4", "Barbeque Crisps");
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
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test
    public void testAddMultipleItemsToStock() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps");
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps");
        IVendingMachineProduct vendingMachineProduct4 = getFactory().makeVendingMachineProduct("A4", "Barbeque Crisps");
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
     */
    @Test
    public void testGetNumberOfOneItem() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        vendingMachine.registerProduct(vendingMachineProduct1);
        for (int i = 0; i < 10; i++) {
            vendingMachine.addItem(vendingMachineProduct1.getLaneCode());
        }
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Haggis Crisps");
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
     * Test to buy an item when it is available.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the specified lane is empty
     */
    @Test
    public void testBuyRegisteredItem() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        vendingMachine.buyItem(vendingMachineProduct.getLaneCode());
        assertEquals(0, vendingMachine.getNumberOfItems(vendingMachineProduct.getLaneCode()));
    }

    /**
     * Test to buy multiple item when it is available.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the specified lane is empty
     */
    @Test
    public void testBuyMultipleProducts() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {

        //intialise three products
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps");
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps");

        //register three products
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
        vendingMachine.registerProduct(vendingMachineProduct3);

        //add three products
        vendingMachine.addItem(vendingMachineProduct1.getLaneCode());
        vendingMachine.addItem(vendingMachineProduct2.getLaneCode());
        vendingMachine.addItem(vendingMachineProduct3.getLaneCode());

        //buy three products
        vendingMachine.buyItem(vendingMachineProduct1.getLaneCode());
        vendingMachine.buyItem(vendingMachineProduct2.getLaneCode());
        vendingMachine.buyItem(vendingMachineProduct3.getLaneCode());

        //since three items were added and three were bought there should be 0 items for the lanecode they were registered to
        assertEquals(0, vendingMachine.getNumberOfItems(vendingMachineProduct1.getLaneCode()));
        assertEquals(0, vendingMachine.getNumberOfItems(vendingMachineProduct2.getLaneCode()));
        assertEquals(0, vendingMachine.getNumberOfItems(vendingMachineProduct3.getLaneCode()));
    }

    /**
     * Test to buy an item that is no longer available.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the specified lane is empty
     */
    @Test(expected = ProductUnavailableException.class)
    public void testBuyUnavailableItems() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        vendingMachine.buyItem(vendingMachineProduct.getLaneCode());
        vendingMachine.buyItem(vendingMachineProduct.getLaneCode());
    }

    /**
     * Test which buys a non existent item, expected to run into runtime exception LaneCodenotRegistered.
     *
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the specified lane is empty
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testBuyNonExistentLaneCodeItem() throws LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.buyItem("A5");
    }

    /**
     * Test which buys an item and shows that there are 0 items available but still there is 1 product which is registered.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the specified lane is empty
     */
    @Test
    public void testNumberOfAvailableItemsAfterPurchaseOfItem() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        vendingMachine.buyItem(vendingMachineProduct.getLaneCode());
        assertEquals(0, vendingMachine.getNumberOfItems(vendingMachineProduct.getLaneCode()));
        assertEquals(1, vendingMachine.getNumberOfProducts());
    }

    /**
     * Test which determines how many times an item was bought.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the specified lane is empty
     */
    @Test
    public void testNumberOfTimesProductWasBought() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(vendingMachineProduct);
        vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        vendingMachine.buyItem(vendingMachineProduct.getLaneCode());
        assertEquals(1, vendingMachine.getNumberOfSales(vendingMachineProduct.getLaneCode()));
    }

    /**
     * Test which determines how many times an item was bought when there is multiple of it in stock.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the specified lane is empty
     */
    @Test
    public void testMultipleNumberOfTimesProductWasBought() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(vendingMachineProduct);
        //add 10 to vending machine
        for (int i = 0; i < 10; i++) {
            vendingMachine.addItem(vendingMachineProduct.getLaneCode());
        }
        //buy item three times
        vendingMachine.buyItem(vendingMachineProduct.getLaneCode());
        vendingMachine.buyItem(vendingMachineProduct.getLaneCode());
        vendingMachine.buyItem(vendingMachineProduct.getLaneCode());
        assertEquals(3, vendingMachine.getNumberOfSales(vendingMachineProduct.getLaneCode()));
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
     * Test which determines the most popular product.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the specified lane is empty
     */
    @Test
    public void testMostPopularProduct() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps");
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps");

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

        //but product1 multiple times
        for (int i = 0; i < 4; i++) {
            vendingMachine.buyItem(vendingMachineProduct1.getLaneCode());
        }

        //determine most popular
        assertEquals(vendingMachineProduct1, vendingMachine.getMostPopular());
    }

    /**
     * Test most popular product when no products are registered, test expected to run into runtime exception LaneCodeNotRegistered.
     *
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testMostPopularProductWithNoRegisteredProducts() throws LaneCodeNotRegisteredException {
        vendingMachine.getMostPopular();
    }

    /**
     * Test most popular product when only one product is registered
     *
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test
    public void testMostPopularWithOneRegisteredItem() throws LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(vendingMachineProduct);
        assertEquals(null, vendingMachine.getMostPopular());
    }

    // Null Tests / Empty Tests

    /**
     * Registering a product with a null lane code, test expected to run into runtime exception NullPointerException.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test(expected = NullPointerException.class)
    public void testNullLaneCode() throws LaneCodeAlreadyInUseException {
        vendingMachineProduct = getFactory().makeVendingMachineProduct(null, "Haggis Crisps");
        vendingMachine.registerProduct(vendingMachineProduct);
    }

    /**
     * Registering a product with a null description.
     *
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Test
    public void testNullDescription() throws LaneCodeAlreadyInUseException {
        vendingMachineProduct = getFactory().makeVendingMachineProduct("A1", null);
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
    @Test(expected = LaneCodeNotRegisteredException.class)
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
     * Test which attempts to buy an item with null lanecode, test is expected to run into runtime exception LanecodeNotRegisteredException.
     *
     * @throws ProductUnavailableException    if the specified lane is empty
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test(expected = LaneCodeNotRegisteredException.class)
    public void testBuyItemWithNullLaneCode() throws ProductUnavailableException, LaneCodeNotRegisteredException {
        vendingMachine.buyItem(null);
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

    // Scenario Tests


    //Scenario 1: register 5 products, add 10 of each registered product, purchase 3 of 2 registered products
    //Scenario 2: register 5 products, add 5 of each registered product, unregister 2 products
    //Scenario 3: register 2 products, add 1 of each product, buy all items, check for most popular
    //Scenario 4: register 1 product, add 1 of it to vending machine, buy it, add 1 again, buy item again, check how many times item was bought

    /**
     * Test which tests scenario 1.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the specified lane is empty
     */
    @Test
    public void testScenarioOne() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        //create 5 products
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps");
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps");
        IVendingMachineProduct vendingMachineProduct4 = getFactory().makeVendingMachineProduct("A4", "Barbeque Crisps");
        IVendingMachineProduct vendingMachineProduct5 = getFactory().makeVendingMachineProduct("A5", "Salt & Vinegar Crisps");

        //register 5 products
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
        vendingMachine.registerProduct(vendingMachineProduct3);
        vendingMachine.registerProduct(vendingMachineProduct4);
        vendingMachine.registerProduct(vendingMachineProduct5);

        //add 10 of each product to the vending machine
        for (int i = 0; i < 10; i++) {
            vendingMachine.addItem(vendingMachineProduct1.getLaneCode());
            vendingMachine.addItem(vendingMachineProduct2.getLaneCode());
            vendingMachine.addItem(vendingMachineProduct3.getLaneCode());
            vendingMachine.addItem(vendingMachineProduct4.getLaneCode());
            vendingMachine.addItem(vendingMachineProduct5.getLaneCode());
        }

        //buy 3 of 2 products
        for (int i = 0; i < 3; i++) {
            vendingMachine.buyItem(vendingMachineProduct1.getLaneCode());
            vendingMachine.buyItem(vendingMachineProduct2.getLaneCode());
        }

        //results
        assertEquals(7, vendingMachine.getNumberOfItems(vendingMachineProduct1.getLaneCode()));
        assertEquals(7, vendingMachine.getNumberOfItems(vendingMachineProduct2.getLaneCode()));
        assertEquals(10, vendingMachine.getNumberOfItems(vendingMachineProduct3.getLaneCode()));
        assertEquals(3, vendingMachine.getNumberOfSales(vendingMachineProduct1.getLaneCode()));
        assertEquals(3, vendingMachine.getNumberOfSales(vendingMachineProduct2.getLaneCode()));
    }


    /**
     * Test which tests scenario 2.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     */
    @Test
    public void testScenarioTwo() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {

        //create 5 products
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps");
        IVendingMachineProduct vendingMachineProduct3 = getFactory().makeVendingMachineProduct("A3", "Prawn Cocktail Crisps");
        IVendingMachineProduct vendingMachineProduct4 = getFactory().makeVendingMachineProduct("A4", "Barbeque Crisps");
        IVendingMachineProduct vendingMachineProduct5 = getFactory().makeVendingMachineProduct("A5", "Salt & Vinegar Crisps");

        //register 5 products
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);
        vendingMachine.registerProduct(vendingMachineProduct3);
        vendingMachine.registerProduct(vendingMachineProduct4);
        vendingMachine.registerProduct(vendingMachineProduct5);

        //add 5 of each product to the vending machine
        for (int i = 0; i < 5; i++) {
            vendingMachine.addItem(vendingMachineProduct1.getLaneCode());
            vendingMachine.addItem(vendingMachineProduct2.getLaneCode());
            vendingMachine.addItem(vendingMachineProduct3.getLaneCode());
            vendingMachine.addItem(vendingMachineProduct4.getLaneCode());
            vendingMachine.addItem(vendingMachineProduct5.getLaneCode());
        }

        //unregister two items
        vendingMachine.unregisterProduct(vendingMachineProduct1);
        vendingMachine.unregisterProduct(vendingMachineProduct2);

        //result
        assertEquals(3, vendingMachine.getNumberOfProducts());
    }

    /**
     * Test which tests scenario 3.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the product is not available to buy
     */
    @Test
    public void testScenarioThree() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {

        //create 2 products
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        IVendingMachineProduct vendingMachineProduct2 = getFactory().makeVendingMachineProduct("A2", "Tomato Ketchup Crisps");

        //register 2 products
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct2);

        //add one of each item
        vendingMachine.addItem(vendingMachineProduct1.getLaneCode());
        vendingMachine.addItem(vendingMachineProduct2.getLaneCode());

        //buy both items
        vendingMachine.buyItem(vendingMachineProduct1.getLaneCode());
        vendingMachine.buyItem(vendingMachineProduct2.getLaneCode());

        //get most popular item
        IVendingMachineProduct popular = vendingMachine.getMostPopular();

        //first item should be most popular
        assertEquals(popular, vendingMachineProduct1);

    }

    /**
     * Test which tests scenario 4.
     *
     * @throws LaneCodeAlreadyInUseException  if duplicate lanecode exists
     * @throws LaneCodeNotRegisteredException if item is not registered
     * @throws ProductUnavailableException    if the product is not available to buy
     */
    @Test
    public void testScenarioFour() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {

        //create 1 item
        IVendingMachineProduct vendingMachineProduct1 = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");

        //register 1 item
        vendingMachine.registerProduct(vendingMachineProduct1);

        //add 1 of it
        vendingMachine.addItem(vendingMachineProduct1.getLaneCode());

        //buy it
        vendingMachine.buyItem(vendingMachineProduct1.getLaneCode());

        //add 1 of the item again
        vendingMachine.addItem(vendingMachineProduct1.getLaneCode());

        //buy it again
        vendingMachine.buyItem(vendingMachineProduct1.getLaneCode());

        //check how many times it was sold and how many is left
        assertEquals(2, vendingMachine.getNumberOfSales(vendingMachineProduct1.getLaneCode()));
        assertEquals(0, vendingMachine.getNumberOfItems(vendingMachineProduct1.getLaneCode()));

    }
}

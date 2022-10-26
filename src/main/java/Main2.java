import model.*;
import service.impl.*;
import utils.CartPriorityComparator;
import utils.Docs;
import utils.FileChooser;
import utils.Qualification;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.util.*;

public class Main2 extends FileChooser {
    private final JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private final FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .xlsx or .csv files", "xlsx", "csv");
    private final ArrayList<Product> products = new ArrayList<>();
    private  static final Queue<String> customerQueue = new LinkedList<>();
    private  static final PriorityQueue<Cart> priorityQueue = new PriorityQueue<>(new CartPriorityComparator());
    private  static final Manager manager = new Manager("Michael", "Male", 32, 1);
    private  static final Applicant applicant = new Applicant("Doris", "Female", 25, Qualification.SSCE.name());
    private  static final Applicant applicant1 = new Applicant("Smith", "Male", 28, Qualification.BSC.name());
    private  static final Cashier cashier = new Cashier("Doris", "Female", 25, 2); //Polymorphism
    private  static final Customer customer = new Customer("Henry", "Male", 43, 2111800.0);
    private  static final Customer customer1 = new Customer("Yinka", "Male", 43, 25000.0);
    private  static final Customer customer3 = new Customer("Agatha", "Female", 30, 11500.0);
    private  static final ManagerServiceImpl managerService = new ManagerServiceImpl();
    private  static final CashierServiceImpl cashierService = new CashierServiceImpl();
    private  static final CustomerServiceImpl customerService = new CustomerServiceImpl();
    private  static final CartServiceImpl cart = new CartServiceImpl();
    private  static final StoreDBImpl storeDB = new StoreDBImpl();

    public static void main(String[] args) {
        Main2 main = new Main2();
        main.openFileChooser(main);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String com = evt.getActionCommand();

        if (com.equals("save")) {
            onSaveButtonClick(file);
        } else {
            onOpenButtonClick();
        }
    }

    private void onOpenButtonClick() {
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setDialogTitle("Select a .xlsx or .csv file");
        jFileChooser.addChoosableFileFilter(restrict);

        int r = jFileChooser.showOpenDialog(null);
        if (r != JFileChooser.APPROVE_OPTION)
            jLabel.setText("the user cancelled the operation");

        if (r == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser.getSelectedFile().getAbsolutePath();
            jLabel.setText(file);
        }
    }

    private void onSaveButtonClick(String file) {
        if (file.isBlank())
            System.out.println("No file was selected");
        else {
            String fileType = file.substring(file.lastIndexOf("."));

            if (fileType.equals(".xlsx")) {
                var result = Docs.readExcelSheet(file);
                System.out.println(Arrays.deepToString(result));
                for (int i = 1; i < result.length; i++) {
                    products.add(new Product(result[i][0], result[i][1], Integer.parseInt(result[i][2]), result[i][3], result[i][4], result[i][5], result[i][6], Double.parseDouble(result[i][7])));
                }
            }

            if (fileType.equals(".csv")) {
                var result = Docs.readCSV(file);
                for (String[] arrayProducts : result) {
                    System.out.println(Arrays.toString(arrayProducts));
                    products.add(new Product(arrayProducts[0], arrayProducts[1], Integer.parseInt(arrayProducts[2]), arrayProducts[3], arrayProducts[4], arrayProducts[5], arrayProducts[6], Double.parseDouble(arrayProducts[7])));
                }
            }
            jFrame.dispose();
            storeActions(products);
        }
    }

    private void storeActions(ArrayList<Product> products) {
        ProductsDBImpl productsDB = new ProductsDBImpl();
        productsDB.setProducts(products);
        Store storeProducts = storeDB.getStoreProducts(productsDB);

        Cashier cashierToHire = managerService.hireCashier(applicant, manager);
        if (cashierToHire == null)
            System.out.println("\nQualification less than BSC or MSC\n");

        Cashier cashier2 = managerService.hireCashier(applicant1, manager);

        //First Customer Buy Product
        String customerBuyResult = customerService.oldBuy(customer, "RICE", storeProducts, 16);
        System.out.println(customerBuyResult);
        String customerBuyResult2 = customerService.oldBuy(customer, "BEANS", storeProducts, 4);
        System.out.println(customerBuyResult2);
        //Second Customer Buy Product
        String customer1BuyResult = customerService.oldBuy(customer1, "RICE", 2022, storeProducts, 3);
        System.out.println(customer1BuyResult);
        //Third Customer Buy Product
        String customer3BuyResult = customerService.oldBuy(customer3, "Cod", 2022, storeProducts, 1);
        System.out.println(customer3BuyResult);


        Cart customerCart = cart.createCart(customer.getProductBoughtList(), customer.getId(), customer.getName());
        Cart customerCart1 = cart.createCart(customer1.getProductBoughtList(), customer1.getId(), customer1.getName());
        Cart customerCart3 = cart.createCart(customer3.getProductBoughtList(), customer3.getId(), customer3.getName());

        priorityQueue.add(customerCart1);
        customerQueue.add(customer1.getName());
        priorityQueue.add(customerCart3);
        customerQueue.add(customer3.getName());
        priorityQueue.add(customerCart);
        customerQueue.add(customer.getName());

        System.out.println("\n" + priorityQueue);
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
        System.out.println("");
        System.out.println(customerQueue);
        while (!customerQueue.isEmpty()) {
            System.out.println(customerQueue.poll());
        }

        //Customers buying products that throws exception
        Customer customer2 = new Customer("Angela", "Female", 21, 1000.0);
        Customer customer4 = new Customer("Herschel", "Male", 50, 10000.0);
    }

    public void buyExceptions1(CustomerServiceImpl customerService, Customer customer2, Store storeProducts) {
        String customer2BuyResult = customerService.oldBuy(customer2, "beankS", storeProducts, 1);
        System.out.println(customer2BuyResult);
    }

    public void buyExceptions2(CustomerServiceImpl customerService, Customer customer4, Store storeProducts) {
        String customer4BuyResult = customerService.oldBuy(customer4, "bEaNs", 2025, storeProducts, 1);
        System.out.println(customer4BuyResult);
    }
}

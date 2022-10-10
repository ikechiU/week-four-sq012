import model.*;
import service.impl.*;
import utils.Docs;
import utils.FileChooser;
import utils.Qualification;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Main2 extends FileChooser {
    private final JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private final FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .xlsx or .csv files", "xlsx", "csv");
    private final ArrayList<Product> products = new ArrayList<>();

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

        Manager manager = new Manager("Michael", "Male", 32, 1);
        Applicant applicant = new Applicant("Doris", "Female", 25, Qualification.SSCE.name());
        Applicant applicant1 = new Applicant("Smith", "Male", 28, Qualification.BSC.name());
        Staff cashier = new Cashier("Doris", "Female", 25, 2); //Polymorphism
        Customer customer = new Customer("Henry", "Male", 43, 2000.0);
        Customer customer3 = new Customer("Agatha", "Female", 30, 3000.0);

        ManagerServiceImpl managerService = new ManagerServiceImpl();
        CashierServiceImpl cashierService = new CashierServiceImpl();
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        StoreDBImpl storeDB = new StoreDBImpl();

        Store storeProducts = storeDB.getStoreProducts(productsDB);

        Cashier cashierToHire = managerService.hireCashier(applicant, manager);
        if (cashierToHire == null)
            System.out.println("\nQualification less than BSC or MSC\n");

        Cashier cashier2 = managerService.hireCashier(applicant1, manager);

        String customerBuyResult = customerService.buy(customer, "RICE", storeProducts, 1);
        System.out.println(customerBuyResult);

        ((Cashier) cashier).setCustomer(customer);
        String cashierSellResult = cashierService.sell((Cashier) cashier, customer.getProductName());
        System.out.println(cashierSellResult + "\n");

        String customer3BuyResult = customerService.buy(customer3, "RICE", 2022, storeProducts, 1);
        System.out.println(customer3BuyResult);

        assert cashier2 != null;
        cashier2.setCustomer(customer3);
        String cashier3SellResult = cashierService.sell(cashier2, customer3.getProductName());
        System.out.println(cashier3SellResult + "\n");

        Customer customer2 = new Customer("Angela", "Female", 21, 2000.0);
        Customer customer4 = new Customer("Herschel", "Male", 50, 2000.0);

        //buyExceptions1(customerService, customer2, storeProducts);
        //buyExceptions2(customerService, customer4, storeProducts);
    }

    public void buyExceptions1(CustomerServiceImpl customerService, Customer customer2, Store storeProducts) {
        String customer2BuyResult = customerService.buy(customer2, "beankS", storeProducts, 1);
        System.out.println(customer2BuyResult);
    }

    public void buyExceptions2(CustomerServiceImpl customerService, Customer customer4, Store storeProducts) {
        String customer4BuyResult = customerService.buy(customer4, "bEaNs", 2025, storeProducts, 1);
        System.out.println(customer4BuyResult);
    }
}

import dao.CoffeeHibDAO;
import dao.ProductHibDAO;
import dao.SupplierHibDAO;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import model.Coffee;
import model.Product;
import model.Supplier;

import java.util.List;

public class Main {

       public static void main(final String[] args)  {
           SeContainerInitializer initializer = SeContainerInitializer.newInstance();
           final SeContainer container = initializer.initialize();

           ProductHibDAO proddao = container.select(ProductHibDAO.class).get();
           CoffeeHibDAO cdao = container.select(CoffeeHibDAO.class).get();
           SupplierHibDAO sdao = container.select(SupplierHibDAO.class).get();


           List<Product> listProducts;
           listProducts = proddao.getAll();
           System.out.println("List of products: ");
           listProducts.forEach((c) -> {
               System.out.println(c);
           });


           List<Coffee> listCoffees;
           listCoffees = cdao.getAll();
           System.out.println("List of coffees: ");
           listCoffees.forEach((c) -> {
               System.out.println(c);
           });

           //One to one relationships
           //Delete: Cascade.REMOVE to coffee.EncriptedCode
           //Coffee c= cdao.get("French_Roast_Decaf");
           //System.out.println("Deleting Coffee: "+c);
           //cdao.delete(c);

           //Add: Cascade.PERSIST
           //With auto-generated keys, no need to specify ids
//           Coffee c= listCoffees.get(0);
//           c.setId(8);
//           c.getProduct().setId_prod(8);
//           c.getEncriptedCode().setCode(123);
//           cdao.add(c);





//           System.out.println("Supplier 49: " + sdao.get(49));
//
//           System.out.println("Coffee with namedQuery: " + cdao.get("Colombian"));
//
//           System.out.println("Adding new supplier");
//         Supplier newSupp= new Supplier(1,"st. bla bla", "Madrid", "SP");
//           sdao.add(newSupp);
//
//           List<Supplier> listSuppliers;
//           listSuppliers = sdao.getAll();
//           System.out.println("List of Suppliers: ");
//           listSuppliers.forEach((c) -> {
//               System.out.println(c);
//           });
//
//           System.out.println("Modifying supplier's address");
//           newSupp.setStreet("Paseo del prado");
//           sdao.update(newSupp);
//           System.out.println("Supplier 1: " + sdao.get(1));
//
//           System.out.println("Deleting supplier");
//           sdao.delete(new Supplier(1));
////
//           listSuppliers = sdao.getAll();
//           System.out.println("List of Suppliers: ");
//           listSuppliers.forEach((c) -> {
//               System.out.println(c);
//           });
//

       }
}
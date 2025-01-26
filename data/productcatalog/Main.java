package data.productcatalog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {

        ProductTemplate product = new ProductTemplate(args[0]);


        ByteArrayOutputStream byte1 = new ByteArrayOutputStream(512);

        ObjectOutputStream out = new ObjectOutputStream(byte1);


        out.writeObject(product);
        
        System.out.println(Base64.getEncoder().encodeToString(byte1.toByteArray()));

        
    
    }
}

class ProductTemplate implements Serializable
{
    static final long serialVersionUID = 1L;

    private final String id;

    public ProductTemplate(String id)
    {
        this.id = id;
    }


}




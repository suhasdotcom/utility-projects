package beantry;

import java.beans.*;

public class BeanInfoTry {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Address address = new Address();
        address.setPIN(411047);
        address.setLine1("Malti Sadan");
        address.setLine2("Adarsh Nagar");
        Person person = new Person();
        person.setName("Suhas");
        person.setAge(29);
        person.setAddress(address);

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println("Property: " + propertyDescriptor.getName());
            System.out.println("Getter: " + propertyDescriptor.getReadMethod());
            System.out.println("Setter: " + propertyDescriptor.getWriteMethod());
            System.out.println();
        }


        try(XMLEncoder xmlEncoder = new XMLEncoder(System.out)) {
            xmlEncoder.writeObject(person);
        }
    }
}


package lab02.util;

import java.io.*;
import java.util.ArrayList;

public class GFile<T> {
    private final File file;

    public GFile(String path) {
        this.file = new File(path);
    }

    public ArrayList<T> readObject(String notification){
        ArrayList<T> result = new ArrayList<T>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0){
                T object = (T) objectInputStream.readObject();
                if (object != null){
                    result.add(object);
                }
            };
            objectInputStream.close();
            fileInputStream.close();
            System.out.println(notification);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void writeObject(ArrayList<T> listObject, String notification){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutputStream);
            for (T object: listObject) {
                objectOutStream.writeObject(object);
            }
            objectOutStream.close();
            fileOutputStream.close();
            System.out.println(notification);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

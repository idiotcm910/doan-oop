package lib;

import java.util.ArrayList;

public interface IListFile
{
    void readListDataInDatabase(final ArrayList<String> p0);
    
    ArrayList<String> writeListDataInDatabase();
}

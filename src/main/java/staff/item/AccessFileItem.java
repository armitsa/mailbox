package staff.item;

import java.io.IOException;

public interface AccessFileItem {

    void setItems(Item item) throws IOException;
    Item getItemList() throws IOException;



}

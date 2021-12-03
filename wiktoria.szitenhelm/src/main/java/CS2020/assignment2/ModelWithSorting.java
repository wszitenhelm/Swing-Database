/**package CS2020.assignment2;
import javax.swing.AbstractListModel;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;


public class ModelWithSorting extends AbstractListModel {
    ArrayList<Artist> artists;
    
    public static sortList() {
        ListModel model = this.getModel();
        String[] strings = new String[model.getSize()];
        for(int i=0;i<strings.length;i++){
            strings[i]=model.getElementAt(i).toString();
        }
        Arrays.sort(strings);
        jl.setListData(strings);
        
    } 
}

    /*public static JList sortJList(JList list) {
        ListModel model = list.getModel();
        String[] strings = new String[model.getSize()];
        for(int i=0;i<strings.length;i++){
            strings[i]=model.getElementAt(i).toString();
        }
        Arrays.sort(strings);
        list.setListData(strings);
        return list;
    }*/
        

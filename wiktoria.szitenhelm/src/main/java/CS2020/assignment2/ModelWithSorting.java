package CS2020.assignment2;

import java.util.Collections;
import java.util.ArrayList;
import javax.swing.*;

//change E for different letter

public class ModelWithSorting<E extends Comparable<E>> extends AbstractListModel<E> {
    ArrayList<E> elements;
    
    ModelWithSorting() {
        this.elements = new ArrayList<>();    
    }
    
    //Sort the contents of the list
    public void sort() {
        Collections.sort(this.elements);
        fireContentsChanged(this, 0, this.elements.size());
    }
    
    public void addElement(E toAdd) {
        this.elements.add(toAdd);
        fireContentsChanged(this, this.elements.size() - 1, this.elements.size());
    }
    
    public void remove(int i) {
        this.elements.remove(i);
        fireContentsChanged(this, i, i);
    }
    
    @Override
    public int getSize() {
        return this.elements.size();
    }
    
    @Override 
    public E getElementAt(int i) {
        return this.elements.get(i);
    }
}

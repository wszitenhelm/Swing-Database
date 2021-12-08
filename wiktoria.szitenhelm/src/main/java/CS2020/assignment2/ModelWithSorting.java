package CS2020.assignment2;

import java.util.Collections;
import java.util.ArrayList;
import javax.swing.*;

/**
* This class was mostly created for sorting list.
* This public class extends Comparable and AbstractListModel.
*/
public class ModelWithSorting<E extends Comparable<E>> extends AbstractListModel<E> {
    ArrayList<E> elements;
    
/**
 * Class constructor that initializes elements. 
 */
    ModelWithSorting() {
        this.elements = new ArrayList<>();    
    }
 
/**
 * Method for sorting elements in the ArrayList called elements.
 */
    public void sort() {
        Collections.sort(this.elements);
        fireContentsChanged(this, 0, this.elements.size());
    }

/**
 * Method for adding toAdd element to the ArrayList called elements.
 * @param toAdd
 */
    public void addElement(E toAdd) {
        this.elements.add(toAdd);
        fireContentsChanged(this, this.elements.size() - 1, this.elements.size());
    }

/**
 * Method for removing element at index i from the ArrayList called elements.
 * @param i
 */
    public void remove(int i) {
        this.elements.remove(i);
        fireContentsChanged(this, i, i);
    }

/**
 * This method overrides getSize() method.
 * Returns size of the ArrayList called elements.
 * @return size of the ArrayList
 */
    @Override
    public int getSize() {
        return this.elements.size();
    }
    
/**
 * This method overrides getElementAt() method.
 * Returns element at index x from the ArrayList called elements.
 * @param x
 * @return element at index x from the ArrayList
 */    
    @Override 
    public E getElementAt(int x) {
        return this.elements.get(x);
    }
}

import list.ArrayBackedList;
import list.Position;
import list.Sequence;


/**
 * Implementation of an ArraySequence,
 * which combines lists and positional lists, storing them using an Array.
 *
 * @author Saqlain Anjum
 * @version 1.0.0
 */
public class ArraySequence<E> implements Sequence<E> {


    /**
     * An array-backed list of Sequence Nodes.
     */
    ArrayBackedList<SequenceNode<E>> items;


    /**
     * An ArraySequence Constructor that is created without specific size.
     */
    public ArraySequence() {
        items = new ArrayBackedList<SequenceNode<E>>();
    }


    /**
     * An ArraySequence Constructor that is created with a specified size.
     *
     * @param size The specified size.
     */
    public ArraySequence(int size) {
        items = new ArrayBackedList<SequenceNode<E>>(size);
    }


    /**
     * Builds the formatted string representation of an ArraySequence object.
     *
     * @return Formatted string representation of an ArraySequence object.
     */
    @Override
    public String toString() {
        StringBuilder newString = new StringBuilder();
        newString.append("{");
        for (int i = 0; i < size(); i++) {
            SequenceNode<E> currentNode = items.get(i);
            newString.append("(").append(currentNode.index).append(",").append(currentNode.getElement()).append(")");
            if (i + 1 != size()) {
                newString.append(",");
            } else {
                newString.append("}");
            }
        }
        return newString.toString();
    }


    /**
     * A method which returns the size of the ArraySequence.
     *
     * @return The size of the ArraySequence.
     */
    public int size() { // universal size method
        return items.size();
    }

    /**
     * A method which checks to see if the ArraySequence is empty.
     *
     * @return A boolean condition indicating whether
     * the ArraySequence is empty.
     */
    public boolean isEmpty() { // universal isEmpty method
        return items.isEmpty();
    }

    /**
     * Returns (but does not remove) the element at index i.
     *
     * @param index The index of the element to return.
     * @return The element at the specified index.
     */
    public E get(int index) {
        Position<E> returnedObject = atIndex(index);
        return returnedObject.getElement();
    }

    /**
     * Replaces the element at the specified index, and returns the element
     * previously stored.
     *
     * @param index   The index of the element to replace.
     * @param element the new element to be stored.
     * @return the previously stored element.
     */
    public E set(int index, E element) {
        SequenceNode<E> newNode = new SequenceNode<E>(element, index);
        SequenceNode<E> prevElement = items.set(index, newNode);
        return prevElement.getElement();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting
     * all subsequent elements in the list one position further to make room.
     *
     * @param index   The index at which the new element should be stored.
     * @param element The new element to be stored.
     */
    public void add(int index, E element) {
        SequenceNode<E> toAdd = new SequenceNode<E>(element, index);
        items.add(index, toAdd);
    }

    /**
     * Removes and returns the element at the given index, shifting all
     * subsequent elements in the list one position closer to the front.
     *
     * @param index The index of the element to be removed.
     * @return The element that had be stored at the given index.
     */
    public E remove(int index) {
        E removedObject = get(index);
        items.remove(index);
        return removedObject;
    }

    /**
     * Returns the first Position in the list.
     *
     * @return the first Position in the list (or null, if empty).
     */
    public Position<E> first() {
        return (isEmpty()) ? null : atIndex(0);
    }

    /**
     * Returns the last Position in the list.
     *
     * @return the last Position in the list (or null, if empty).
     */
    public Position<E> last() {
        return (isEmpty()) ? null : atIndex(size() - 1);
    }

    /**
     * Returns the Position immediately before the given position.
     *
     * @param item A Position of the list.
     * @return The Position of the preceding element (or null, if p is first).
     */
    public Position<E> before(Position<E> item) {
        if (isEmpty()) {
            return null;
        }
        int indexOfItem = indexOf(item);
        Position<E> beforeItem = atIndex(indexOfItem - 1);
        return beforeItem;
    }

    /**
     * Returns the Position immediately after Position p.
     *
     * @param item A Position of the list.
     * @return The Position of the following element (or null, if p is last)
     */
    public Position<E> after(Position<E> item) {
        if (isEmpty()) {
            return null;
        }
        int indexOfItem = indexOf(item);
        Position<E> afterItem = atIndex(indexOfItem + 1);
        return afterItem;
    }

    /**
     * Inserts an element at the front of the list.
     *
     * @param element the new element.
     * @return The Position representing the location of the new element.
     */
    public Position<E> addFirst(E element) {
        add(0, element);
        for (int i = 0; i < size(); i++) {
            SequenceNode<E> currentItem = items.get(i);
            currentItem.index = i;
        }
        return first();
    }

    /**
     * Inserts an element at the back of the list.
     *
     * @param element The new element.
     * @return The Position representing the location of the new element.
     */
    public Position<E> addLast(E element) {
        add(size(), element);
        return last();
    }

    /**
     * Inserts an element immediately before the given Position.
     *
     * @param item    The Position before which the insertion takes place.
     * @param element The new element.
     * @return The Position representing the location of the new element.
     */
    public Position<E> addBefore(Position<E> item, E element) {
        int beforeIndex = indexOf(item);
        add(beforeIndex, element);
        return before(item);
    }

    /**
     * Inserts an element immediately after the given Position.
     *
     * @param item    The Position after which the insertion takes place.
     * @param element The new element.
     * @return the Position representing the location of the new element
     */
    public Position<E> addAfter(Position<E> item, E element) {
        int afterIndex = indexOf(item);
        add(afterIndex, element);
        return after(item);
    }

    /**
     * Replaces the element stored at the given Position and returns the
     * replaced element.
     *
     * @param position The Position of the element to be replaced.
     * @param element  The new element.
     * @return The replaced element.
     */
    public E set(Position<E> position, E element) {
        SequenceNode<E> positionNode = (SequenceNode<E>) position;
        positionNode.element = element;
        return position.getElement();
    }

    /**
     * Removes the element stored at the given Position and returns it.
     * The given position is invalidated as a result.
     *
     * @param item The Position of the element to be removed.
     * @return The removed element.
     */
    public E remove(Position<E> item) {
        int indexToRemove = indexOf(item);
        items.remove(indexToRemove);
        return item.getElement();
    }

    /**
     * Returns the position containing the element at the given index.
     *
     * @param index The index of the element.
     * @return The position of the element at the specified index.
     */
    public Position<E> atIndex(int index) {
        return items.get(index);
    }

    /**
     * Returns the index of the element stored at the given Position.
     *
     * @param item The Position of the element.
     * @return The index of the element at the specified Position.
     */
    public int indexOf(Position<E> item) {
        SequenceNode<E> itemSequenceNode = (SequenceNode<E>) item;
        return itemSequenceNode.index;
    }

    /**
     * A nested SequenceNode class which implements the Position ADT.
     *
     * @author Saqlain Anjum
     * @version 1.0.0
     */
    private static class SequenceNode<E> implements Position<E> {

        /**
         * The index for the SequenceNode backed up in an Array.
         */
        private int index = 0;
        /**
         * The element stored in a SequenceNode object.
         */
        private E element;

        /**
         * A SequenceNode object constructor.
         *
         * @param element the element stored in the object.
         * @param index   the index of the element for the Array-backed implementation.
         */
        public SequenceNode(E element, int index) {
            this.element = element;
            this.index = index;
        }

        /**
         * A utility method for accessing the element stored in a SequenceNode.
         *
         * @return The element stored.
         * @throws IllegalStateException
         */
        public E getElement() throws IllegalStateException {
            if (element.equals(null)) {
                throw new IllegalStateException("Position no longer valid!");
            }
            return element;
        }
    }
}
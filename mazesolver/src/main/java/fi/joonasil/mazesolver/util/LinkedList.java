
package fi.joonasil.mazesolver.util;

/**
 * Linkitetty lista tietorakenne. Remove metodia ei tarvitse ainakaan 
 * tällä hetkellä toteuttaa.
 * @author Joonas
 * @param <T> Linkitetyn listan tyyppi.
 */
public class LinkedList <T>{
    private Node<T> first;
    private Node<T> last;
    private int size;
    
    /**
     * Konstruktori luo uuden tyhjän linkitetyn listan.
     */
    public LinkedList() {
        first = new Node<>(null,null);
        size = 0;
    }
    
    /**
     * Palauttaa linkitetyn listan pituuden.
     * @return 
     */
    public int size(){
        return this.size;
    }
      
    /**
     * Lisää objektin linkitettyyn listaan.
     * @param data lisättävä olio
     */
    public void add(T data) {
        Node<T> l = last;
        Node<T> newNode = new Node(data, null);
        last = newNode;
        if(l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }
    
    /**
     * Palauttaa objektin parametrina annetussa indeksissä. 
     * @param index Halutun 
     * @return 
     */
    public T get(int index) {
        checkIndex(index);
        return node(index).data;
    }
    
    /**
     * Viesti, joka esitetään, jos tulee Index out of bounds exception. 
     * @param index Indeksi, mikä aiheutti out of bounds exceptionin.
     * @return Viesti.
     */
    private String outOfBoundsMessage(int index) {
        return "Index: " + index + ", Size: " + size;
    }
    
    /**
     * Tarkistaa onko indeksi sallitulla välillä.
     * @param index Tarkastettava indeksi.
     */
    private void checkIndex(int index) {
        if(!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException(outOfBoundsMessage(index));
    }
    
    /**
     * Luokka linkitetyn listan yksittäiselle nodelle.
     * @param <T> Linkitetyn listan tietotyyppi.
     */
    private static class Node<T>{
        T data;
        Node<T> next;
        
        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
    
    /**
     * Palauttaa noden linkitetyn listan indeksissä index;
     * @param index palautettavan noden indeksi.
     * @return 
     */
    Node<T> node(int index) {
        Node<T> x = first;
        for(int i = 0; i < index; i++)
            x = x.next;
        return x;
    } 
}

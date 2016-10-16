
package fi.joonasil.mazesolver.util;

/**
 * -- HUOM! --
 * 
 * Linkitettyä listaa ei tällä hetkellä käytetä sovelluksessa, mutta en halua
 * poistaa luokkaa jos vaikka tarvitsen linkitettyä listaa joskus.
 * Luokan voi siis jättää huomioimatta.
 * 
 * -----------
 * 
 * Linkitetty lista tietorakenne.
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
        first = new Node<>(null,null,null);
        size = 0;
    }
    
    /**
     * Palauttaa linkitetyn listan pituuden.
     * @return Linkitetyn listan pituus.
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
        Node<T> newNode = new Node(data, null, l);
        last = newNode;
        if(l == null)
            first = newNode;
        else{
            l.next = newNode;
            newNode.prev = l;
        }
        size++;
    }
    
    /**
     * Palauttaa objektin parametrina annetussa indeksissä. 
     * @param index Haluttu indeksi.
     * @return Objekti parametrina annetussa indeksissä.
     */
    public T get(int index) {
        checkIndex(index);
        return node(index).data;
    }
    
    
    public T removeFirst(){
        T out = first.data;
        first = first.next;
        size--;
        if(size == 0)
            last = null;
        if(first == null)
            return out;
        first.prev = null;
        return out;
    }
    
    public T removeLast(){
        T out = last.data;
        last = last.prev;
        last.next = null;
        size--;
        return out;
    }
    
    public T remove(int index){
        if(index == 0)
            return removeFirst();
        if(index == size-1)
            return removeLast();
        Node<T> out = node(index);
        Node<T> prev = node(index-1);
        prev.next = out.next;
        prev.next.prev = prev;
        size--;
        return out.data;
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
     * Luokka linkitetyn listan yksittäiselle solmu.
     * @param <T> Linkitetyn listan tietotyyppi.
     */
    private static class Node<T>{
        T data;
        Node<T> next;
        Node<T> prev;
        
        Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    
    /**
     * Palauttaa solmun linkitetyn listan indeksissä index;
     * @param index palautettavan solmun indeksi.
     * @return Solmu parametrina annetussa indeksissä.
     */
    Node<T> node(int index) {
        Node<T> x = first;
        for(int i = 0; i < index; i++)
            x = x.next;
        return x;
    } 
}

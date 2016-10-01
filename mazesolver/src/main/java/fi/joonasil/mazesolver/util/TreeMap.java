
package fi.joonasil.mazesolver.util;

import java.util.NoSuchElementException;

/**
 * Tasapainotettu hakupuu kartta, jossa avaimet ovat aina kokonaislukuja ja
 * hakupuu toteutettuna AVL-puuna.
 * @author Joonas
 */
public class TreeMap <T>{
    private Node<T> root;
    
    /**
     * konstruktori luo uuden tyhjän hakupuun.
     */
    public TreeMap(){
        root = null;
    }
    
    /**
     * Lisää puuhun uuden avain-arvo parin ja tarvittaessa tasapainottaa puun.
     * @param key Kokonaislukutyyppinen avain.
     * @param data Arvo, johon avain viittaa.
     */
    public void put(int key, T data){
        Node<T> newNode = insert(key,data);
        Node<T> p = newNode.parent;
        while(p != null){
            if(height(p.left) == height(p.right)+2){
                fixLeft(p);
                return;
            }
            if(height(p.right) == height(p.left)+2){
                fixRight(p);
                return;
            }
            p.height = Math.max(height(p.left), height(p.right))+1;
            p = p.parent;
        }
    }
    
    /**
     * Palauttaa arvon, johon tietty avain viittaa. Jos avainta ei ole puussa,
     * heittää uuden NoSuchElementExeption virheen.
     * @param key Kysytty avain.
     * @return Kysyttyyn avaimeen liittyvä arvo.
     */
    public T get(int key){
        Node<T> node = find(key);
        if(node == null)
            throw new NoSuchElementException("Key: " + key);
        return node.data;
    }
    
    /**
     * Metodi poistaa ja palauttaa parametrina annetun avaimen ja
     * siihen liittyvän arvon puusta ja tarvittaessa tasapainottaa puun.
     * 
     * @param key Avain, johon liittyvä arvo halutaan.
     * @return Poistettu avain-arvo pari.
     */
    public T remove(int key){
        Node<T> node = find(key);
        Node<T> remove = delete(node);
        Node<T> p = remove.parent;
        Node<T> parent;
        while(p != null){
            if(height(p.left) == height(p.right)+2 || height(p.right) == height(p.left)+2){
                parent = p.parent;   
                if(height(p.left) == height(p.right)+2){
                    fixLeft(p);
                }
                if(height(p.right) == height(p.left)+2){
                    fixRight(p);
                }
                p = parent;
            }else{
                p.height = Math.max(height(p.left), height(p.right))+1;
                p = p.parent;
            }
        }
        return remove.data;
    }
    
    /**
     * Palauttaa isoimman indeksin puussa.
     * @return Puun isoin indeksi.
     */
    public int lastKey(){
        Node<T> node = max(root);
        return node.key;
    }
    
    /**
     * Metodi palauttaa totuusarvon siitä, onko puu tyhjä.
     * @return Onko puu tyhjä.
     */
    public boolean isEmpty(){
        return root == null;
    }
    
    /**
     * Metodi vaihtaa parametrina annetun avaimen viittaaman arvon
     * parametrina annettuun arvoon.
     * @param key Avain, jonka viittaama arvo halutaan vaihtaa.
     * @param node Avain-arvo pari, jonka arvoon avain laitetaan viittaamaan.
     * @return Arvon, johon parametrina annettu avain ennen viittasi.
     */
    public T replace(int key, T data){
        Node<T> old = find(key);
        if(old == null)
            return null;
        T oldData = old.data;
        old.data = data;
        return oldData;
    }
    
    /**
     * Metodi palauttaa puun solmun korkeuden.
     * @param node Puun solmu, jonka korkeus halutaan tietää.
     * @return Solmun korkeus. Jos solmua ei ole olemassa, palautetaan -1.
     */
    private int height(Node<T> node){
        if(node == null)
            return -1;
        return node.height;
    }
    
    /**
     * Metodi korjaa parametrina annetun solmun vasemman alipuun tasapainon.
     * @param p Solmu, jonka vasen alipuu on epätasapainossa.
     */
    private void fixLeft(Node<T> p){
        Node<T> parent;
        Node<T> subtree;
        parent = p.parent;
        if(height(p.left.left) > height(p.left.right)){
            subtree = rightRotate(p);
        }else{
            subtree = leftRightRotate(p);
        }
        if(parent == null)
            root = subtree;
        else if(parent.left == p)
            parent.left = subtree;
        else
            parent.right = subtree;
        if(parent != null)
            parent.height = Math.max(height(parent.left), height(parent.right))+1;
    }
    
    /**
     * Metodi korjaa parametrina annetun solmun oikean alipuun tasapainon.
     * @param p Solmu, jonka oikea alipuu on epätasapainossa.
     */
    private void fixRight(Node<T> p){
        Node<T> parent;
        Node<T> subtree;
        parent = p.parent;
        if(height(p.right.right) > height(p.right.left)){
            subtree = leftRotate(p);
        }else{
            subtree = rightLeftRotate(p);
        }
        if(parent == null)
            root = subtree;
        else if(parent.left == p)
            parent.left = subtree;
        else
            parent.right = subtree;
        if(parent != null)
            parent.height = Math.max(height(parent.left), height(parent.right))+1;
    }
    
    /**
     * Lisää puuhun uuden solmun oikealle paikalle, mutta ei tasapainota puuta.
     * @param key Uuden solmun avain.
     * @param data Uuden solmun arvo.
     * @return Uusi solmu oikeassa paikassa puuta.
     */
    private Node<T> insert(int key, T data){
        Node<T> newNode = new Node(key, data, null);
        Node<T> p = null;
        if(root == null) {
            root = newNode;
            return newNode;
        }
        Node<T> x = root;
        while(x != null){
            p = x;
            if(newNode.key < x.key)
                x = x.left;
            else
                x = x.right;
        }
        newNode.parent = p;
        if(newNode.key < p.key)
            p.left = newNode;
        else
            p.right = newNode;
        return newNode;
    }
    
    /**
     * Poistaa solmun puusta, mutta ei tasapainota puuta.
     * @param remove Puusta poistettava solmu.
     * @return Puusta poistettu solmu.
     */
    private Node<T> delete(Node<T> remove){
        Node<T> parent;
        Node<T> child;
        Node<T> succ;
        if(remove.left == null && remove.right == null){
            return removeNone(remove);
        }
        if(remove.left == null || remove.right == null){
            return removeOne(remove);
        }
        succ = min(remove.right);
        remove.key = succ.key;
        child = succ.right;
        parent = succ.parent;
        if(parent.left == succ)
            parent.left = child;
        else
            parent.right = child;
        if(child != null)
            child.parent = parent;
        return succ;
    }
    
    /**
     * Poistaa puusta solmun, jolla ei ole yhtään lasta.
     * @param remove Puusta poistettava solmu.
     * @return Puusta poistettu solmu.
     */
    private Node<T> removeNone(Node<T> remove){
        Node<T> parent = remove.parent;
            if(parent == null){
                root = null;
                return remove;
            }
            if(remove == parent.left)
                parent.left = null;
            else
                parent.right = null;
            return remove;
    }
    
    /**
     * Poistaa puusta solmun, jolla on tasan yksi lapsi.
     * @param remove Poistettava solmu, jolla on yksi lapsi.
     * @return Puusta poistettu solmu.
     */
    private Node<T> removeOne(Node<T> remove){
        Node<T> parent;
        Node<T> child;
        if(remove.left != null)
                child = remove.left;
            else
                child = remove.right;
            parent = remove.parent;
            child.parent = parent;
            if(parent == null){
                root = child;
                return remove;
            }
            if(remove == parent.left)
                parent.left = child;
            else
                parent.right = child;
            return remove;
    }
    
    /**
     * Palauttaa paramatrina annetun (ali)puun solmun, jolla on alin avain.
     * @param node (ali)puun juurisolmu.
     * @return (ali)puun alin avaimen arvo.
     */
    private Node<T> min(Node<T> node){
        if(node.left == null)
            return node;
        Node<T> min = node.left;
        while(min.left != null){
            min = min.left;
        }
        return min;
    }
    
    /**
     * Palauttaa parametrina annetun (ali)puun solmun, jolla on suurin avain.
     * @param node (ali)puun juurisolmu.
     * @return (ali)puun suurin avaimen arvo.
     */
    private Node<T> max(Node<T> node){
        if(node.right == null)
            return node;
        Node<T> max = node.right;
        while(max.right != null){
            max = max.right;
        }
        return max;
    }
    
    /**
     * Palautaa halutun solmun. Jos solmua ei ole olemassa palauttaa null.
     * @param key Halutun solmun avain.
     * @return Haluttu solmu.
     */
    private Node<T> find(int key){
        if(root == null)
            return null;
        Node<T> node = root;
        while(node.key != key){                
            if(key > node.key){
                if(node.right == null)
                    return null;
                node = node.right;
            }else{
                if(node.left == null)
                    return null;
                node = node.left;
            }
        }
        return node;
    }
    
    /**
     * Kiertää parametrina annettua alipuuta oikealle.
     * @param node Epätasapainossa olevan alipuun juuri.
     * @return Alipuun uusi juuri.
     */
    private Node<T> rightRotate(Node<T> node){
        Node<T> node2 = node.left;
        node2.parent = node.parent;
        node.parent = node2;
        node.left = node2.right;
        node2.right = node;
        if(node.left != null)
            node.left.parent = node;
        node.height = Math.max(height(node.left), height(node.right))+1;
        node2.height = Math.max(height(node2.left), height(node2.right))+1;
        return node2;
    }
    
    /**
     * Kiertää parametrina annettua alipuuta vasemmalle.
     * @param node Epätasapainossa olevan alipuun juuri.
     * @return Alipuun uusi juuri.
     */
    private Node<T> leftRotate(Node<T> node){
        Node<T> node2 = node.right;
        node2.parent = node.parent;
        node.parent = node2;
        node.right = node2.left;
        node2.left = node;
        if(node.right != null)
            node.right.parent = node;
        node.height = Math.max(height(node.left), height(node.right))+1;
        node2.height = Math.max(height(node2.left), height(node2.right))+1;
        return node2;
    }
    
    /**
     * Kiertää parametrina annettua alipuuta ensin oikealle ja sitten vasemmalle.
     * @param node Epätasapainossa olevan alipuun juuri.
     * @return Alipuun uusi juuri.
     */
    private Node<T> rightLeftRotate(Node<T> node){
        Node<T> node2 = node.right;
        node.right = rightRotate(node2);
        return leftRotate(node);
    }
    
    /**
     * Kiertää parametrina annettua alipuuta ensin vasemmalle ja sitten oikealle.
     * @param node Epätasapainossa olevan alipuun juuri.
     * @return 
     */
    private Node<T> leftRightRotate(Node<T> node){
        Node<T> node2 = node.left;
        node.left = leftRotate(node2);
        return rightRotate(node);
    }
    
    /**
     * Luokka hakupuun solmulle.
     * @param <T> Solmun tietotyyppi.
     */
    private class Node<T> {
        int key;
        T data;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        int height;
        
        public Node(int key, T data, Node<T> parent){
            this.key = key;
            this.data = data;
            left = null;
            right = null;
            height = 0;
            this.parent = parent;
        }
    }
}

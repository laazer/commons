package com.laazer.common;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.sun.deploy.util.StringUtils;

/**
 * 
 * @author laazer
 *
 * Used in other languages (sometimes referred to as an option),
 * a Box is a list containing 
 * a single element. Boxes are either full or empty
 * @param <T> type
 */
public abstract class Box <T> {
    /**
     * checks to see if this <code>Box</code> is full
     * @return true if the <code>Box</code> is full
     */
    public boolean isFull(){ return false;}
    /**
     * checks to see if this <code>Box</code> is empty
     * @return true if the <code>Box</code> is empty
     */
    public boolean isEmpty(){ return true;}
    
    public final static Box EMPTY = Box.empty();
    
    /**
     * maps the object inside the box by applying the given <code>Function</code>
     * f to it. 
     * @param f a given <code>Function</code>
     * @return a <code>Box</code> containing a mapped version of what was previously inside.
     * if the <code>Box</code> is empty the box will remain empty.
     */
    public <B> Box<B> map(Function<T, B> f){return Box.empty();}
    /**
     * Retrieves what's inside this <code>Box</code>
     * @return whatever is inside this <code>Box</code>
     * throws a nullPointerException if this Box is empty
     */
    public T get() {
        if(this.isEmpty()) {
            throw new NullPointerException("Empty Box");
        }
        else return this.get();
    }
    /** trys to get whats in inside the box but if the box
     * is empty returns the default object
     * @param o default object
     */
    public Object getOrElse(Object o) {
        if(this.isFull()) return this.get();
        else return o;
    }
    /** Trys to get what's in inside the box but if the box
     * is empty returns the default object. this will
     * never change the return type.
     * @param t default object
     */
    public T getOrElseKeepType(T t) {
        if(this.isFull()) return this.get();
        else return t;
    }
    private static Box empty() {
        return new Empty();
    }
    /**
     * Fills a <code>Box</code> with the given item
     * @param cat a given item of some type K
     * @return a filled <code>Box</code> reffered to as an 
     * instance of <code>Full</code>
     */
    public static <K> Box<K> fill(K cat) {
       try { 
        return new Full<K>(cat);
       }
       catch(Exception e){return Box.EMPTY;}
    }
    
    /**
     * converts this <code>Box</code> into a <code>Set</code>
     * @return a <code>Set</code> representation of this <code>Box</code>
     */
    public Set<T> toSet() {
        return Collections.EMPTY_SET;
    }
    /**
     * converts this <code>Box</code> into a <code>List</code>
     * @return a <code>List</code> representation of this <code>Box</code>
     */
    public List<T> toList() {
        return Collections.EMPTY_LIST;
    }

    /**
     * converts this {@code Box} into a google {@code Optional}
     * @return an {@code Optional} representation of this {@code Box}
     */
    public Optional<T> toOptional() {return Optional.absent();}

    @Override
    public String toString() {
        return "Box[]";
    }

    /**
     * Overrides the current value of this {@code Box}
     * with the given value
     * @param newCat of type {@code T}
     */
    public void set(T newCat) {
        throw new NullPointerException("Empty Box");
    }
}

/**
 * class representing a full <code>Box</code>
 * @author Jacob
 * 
 * @param <T> type of item inside of this <code>Box</code>
 */
class Full<T> extends Box<T> {
    private T cat;
    public Full(T cat) {this.cat = cat;}
    @Override
    public boolean isFull() {return true;}
    @Override
    public <B> Box<B> map(Function<T, B> f) {
        return new Full<B>(f.apply(this.cat));
    }
    @Override
    public T get(){return this.cat;}
    
    @Override
    public Set<T> toSet() {
        return Collections.singleton(cat);
    }
    @Override
    public List<T> toList () {
        return Collections.singletonList(cat);
    }
    @Override
    public Optional<T> toOptional() {return Optional.of(cat);}

    @Override
    public boolean equals(Object o) {
        if(o == null) { return false;}
        if(o instanceof Full) {
            return this.get().equals(((Full) o).get());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 42 * this.get().hashCode();
    }

    @Override
    public String toString() {
        return String.format("Box[%s]", this.cat.toString());
    }

    @Override
    public void set(T newCat) {
        this.cat = newCat;
    }
}

@SuppressWarnings("rawtypes")
final class Empty extends Box {
    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        else return o instanceof Empty;
    }
    @Override
    public int hashCode() {return 9001;}
}

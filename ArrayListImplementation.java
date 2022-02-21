/**
 * 
 * Name: Kabir Bagai
 * This file contains my implementation of an ArrayList. The three constructors initialize  
 * MyArrayList 3 different ways but all contain the same instance variables, an Object array called "data" and a variable called "size". 
 * The size instance variable keeps track of the number of valid elements in the ArrayList while the capacity of the Array List is 
 * determined by the length of the "data" array. The other methods contained in the MyArrayList class provide the same functionality as
 * the ones in Java's ArrayList class. 
 */

 //constructs a MyArrayList object and implements methods equivalent to those found in the ArrayList class in Java. 

 class MyArrayList<E>{

    Object[] data; //underlying data structure of ArrayList, stores elements up to certain capacity
    int size; //number of valid elements in array (corresponds to the ArrayList)
    private static final int default_capacity = 5;

    /* constructs MyArrayList when initial capacity isn't given. Default capacity is 5
    */
    public MyArrayList(){
        this.data = new Object[default_capacity]; 
        this.size = 0; 
    }

    /* constructs MyArrayList when initial capacity is given.
    *@param initialCapicity of array
    */

    public MyArrayList(int initialCapacity){


        if(initialCapacity < 0){
            throw new IllegalArgumentException(); 
        }
        else{
            this.data = new Object[initialCapacity];
            this.size = 0; 
        }
    }

     /* constructs MyArrayList object using input array.
     * @param arr- array of elements "E" that is used to initialize a new MyArrayList object 
     */
    public MyArrayList (E[] arr){

        if(arr == null){
            this.data = new Object[default_capacity]; 
            this.size = 0; 
        }
        else{
            this.size = arr.length; 
            this.data = arr; 
        }
        
        

    }

    /*expands capacity of array to twice the original size, the required capacity or the default capacity.
    *@param requiredCapacity- the minimum required capacity of the expanded ArrayList
    */
    public void expandCapacity(int requiredCapacity){

        int current_capacity = this.data.length; 

        if (requiredCapacity < current_capacity){
            throw new IllegalArgumentException(); 
        }

        else if(current_capacity == 0){
            current_capacity = default_capacity; 

        }

        //expands capacity to twice the original length or required capacity

        else if (requiredCapacity >= current_capacity){
            current_capacity = current_capacity * 2; 
            
            if(requiredCapacity >= current_capacity){
                current_capacity = requiredCapacity; 
            }
        }

        MyArrayList<E> new_arrayList  = new MyArrayList<>(current_capacity);

        //copies elements of data to new array with expanded capacity
        
        for(int i = 0; i < this.data.length; i++){
            new_arrayList.data[i] = this.data[i]; 
            
        }

        this.data = new_arrayList.data;

    }

    // returns capacity of array
   
    public int getCapacity(){
        return this.data.length; 
    }

    /*inserts element at specified index of array and shifts all proceeding elements by 1 
    * @param index- index to insert new element
    * @param element- element to be inserted
    */
    public void insert(int index, E element){



        if(index < 0 || index > this.size){
            throw new IndexOutOfBoundsException(); 
        }

        //expands capacity if size >= length
        if (this.size >= this.data.length){
            
            this.expandCapacity(this.data.length);
        }

        else if (this.data.length == 0){
            this.expandCapacity(1); 
        }

        //initializes new ArrayList to copy elements 

        MyArrayList<E> new_arrayList1 = new MyArrayList<>(this.data.length);
        

        //copies the elements up to index to new_arrayList1
        for(int j = 0; j < index; j++){
            new_arrayList1.data[j] = this.data[j]; 
        }

        //inserts element in "index" position
        new_arrayList1.data[index] = element; 

        //copies remaining elements to new_arrayList1

        for(int i = index; i < this.size; i++){
            new_arrayList1.data[i+1] = this.data[i]; 
        }

        this.data = new_arrayList1.data; 
        this.size++; 
        
    }

    /*appends element to the end of the array
    * @param element- element to be appeneded to ArrayList
    */

    public void append(E element){

        //expands capacity of ArrayList if size >= length or length = 0

        if (this.size >= this.data.length){
            this.expandCapacity(this.data.length); 
        }

        else if (this.data.length == 0){
            this.expandCapacity(1); 
            
        }

        //adds element to end of the array

        this.data[size] = element; 
        size++;
    }


    /*inserts element in beginning of array, shifts all other elements 1 position 
    * @param element- element to be prepended to ArrayList
    */
    public void prepend(E element){

        //expands capacity of ArrayList if size >= length or length = 0
        if (this.size >= this.data.length){
            this.expandCapacity(this.data.length); 
        }

        else if (this.data.length == 0){
            this.expandCapacity(1); 
            
        }

        //initialize new ArrayList to copy data 
        MyArrayList<E> new_arrayList3 = new MyArrayList<>(this.data.length);
        

        //set first element of new ArrayList to element
        new_arrayList3.data[0] = element; 

        //copy remaining elements from this.data to new ArrayList
       
        for(int i = 0; i < this.size; i++){
            new_arrayList3.data[i+1] = this.data[i]; 
        }

        this.data = new_arrayList3.data;
        size ++; 


    }

    /*returns the element at the specified index 
    * @param index- index of element to get 
    */
    
    public E get(int index){

         //throws exception if index is less than zero or greater than size 
        if (index < 0 || index > this.size){
            throw new IndexOutOfBoundsException(); 
        }

        return (E)this.data[index]; 
    }

    /*sets the element at the specified index to a new element
    * @param index- index of element to set
    * @param element - new element 
    */

    public E set(int index, E element){

         //throws exception if index is less than zero or greater than size 
        if (index < 0 || index > this.size){
            throw new IndexOutOfBoundsException(); 
        }
        
        Object overwritten_value = this.data[index]; 

        this.data[index] = element; 

        return (E)overwritten_value;
        
    }

    /*remove the item at the specified index and return the removed element 
    *@param index of element to be removed
    *@return element that was removed
    */

    public E remove(int index){

        //throws exception if index is less than zero or greater than size 
        if(index < 0 || index > this.size){
            throw new IndexOutOfBoundsException(); 
        }

        MyArrayList<E> new_arrayList1 = new MyArrayList<>(this.data.length);

        Object removed_element = this.data[index]; 
        
        //copies elements up to index into new array
        for(int j = 0; j < index; j++){
            new_arrayList1.data[j] = this.data[j]; 
        }

        //skips over index and copies remaining elements into new array

        for(int i = index + 1; i < this.size; i++){
            new_arrayList1.data[i-1] = this.data[i]; 
        }

        this.data = new_arrayList1.data; 
        this.size--; 

        return (E) removed_element; 
        
        
    }

    // returns the size of the array, i.e, the number of valid elements in ArrayList
    public int size(){
        return this.size; 
    }
    
 }

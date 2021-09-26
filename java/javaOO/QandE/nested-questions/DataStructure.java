/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
public class DataStructure {
    
    // Create an array
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];
    
    public DataStructure() {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }
    
    private int get(int index) {
        return arrayOfInts[index];
    }
    
    private EvenIterator getNewEvenIterator() {
        return new EvenIterator();
    }
    
    public void printEven() {
        
        // Print out values of even indices of the array
        DataStructureIterator iterator = getNewEvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
    
    public void print(DataStructureIterator iterator) {
        
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
    
    public void print(java.util.function.Function<Integer, Boolean> iterator) {
        
        for (int i = 0; i < SIZE; i++) {
            if (iterator.apply(i)) {
                System.out.print(arrayOfInts[i] + " ");
            }
        }
        System.out.println();
    }
    
    public void printWithPredicate(java.util.function.Predicate<Integer> iterator) {
        
        for (int i = 0; i < SIZE; i++) {
            if (iterator.test(i)) {
                System.out.print(arrayOfInts[i] + " ");
            }
        }
        System.out.println();
    }

    private static boolean isEvenIndex(int i) {
        return i % 2 == 0;
    }

    private static boolean isOddIndex(int i) {
        return !isEvenIndex(i);
    }
    
    interface DataStructureIterator extends java.util.Iterator<Integer> { } 

    // Inner class implements the DataStructureIterator interface,
    // which extends the Iterator<Integer> interface
    
    private class EvenIterator implements DataStructureIterator {
        
        // Start stepping through the array from the beginning
        private int nextIndex = 0;
        
        public boolean hasNext() {
            
            // Check if the current element is the last in the array
            return (nextIndex <= SIZE - 1);
        }        
        
        public Integer next() {
            
            // Record a value of an even index of the array
            Integer retValue = get(nextIndex);
            
            // Get the next even element
            nextIndex += 2;
            return retValue;
        }
    }
    
    public static void main(String s[]) {
        
        // Fill the array with integer values and print out only
        // values of even indices
        DataStructure ds = new DataStructure();
        
        System.out.println("Results of `ds.printEven();`:");
        ds.printEven();
        
        System.out.println("Results of `ds.print(ds.getNewEvenIterator());`:");
        ds.print(ds.getNewEvenIterator());
        
        System.out.println("Results of `ds.print(new DataStructureIterator() {...});`:");
        ds.print(new DataStructureIterator() {
            private int nextIndex = 1;
        
            public boolean hasNext() {
                
                // Check if the current element is the last in the array
                return (nextIndex <= SIZE - 1);
            }        
            
            public Integer next() {
                
                // Record a value of an odd index of the array
                Integer retValue = ds.get(nextIndex);
                
                // Get the next odd element
                nextIndex += 2;
                return retValue;
            }
        });
        
        System.out.println("Results of `ds.print(indexOfArray -> indexOfArray % 2 == 0);`:");
        ds.print(indexOfArray -> indexOfArray % 2 == 0);
        
        System.out.println("Results of `ds.print(indexOfArray -> indexOfArray % 2 != 0);`:");
        ds.print(indexOfArray -> indexOfArray % 2 != 0);
        
        System.out.println("Results of `ds.printWithPredicate(indexOfArray -> indexOfArray % 2 == 0);`:");
        ds.printWithPredicate(indexOfArray -> indexOfArray % 2 == 0);
        
        System.out.println("Results of `ds.print(DataStructure::isEvenIndex);`:");
        ds.print(DataStructure::isEvenIndex);
        
        System.out.println("Results of `ds.print(DataStructure::isOddIndex);`:");
        ds.print(DataStructure::isOddIndex);
    }
}

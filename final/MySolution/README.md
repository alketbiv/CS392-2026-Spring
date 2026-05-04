# Final Exam Solutions

## Final_00 (0 points)
Given code. Reads pg2701.txt and returns a lazy stream of characters using MyLibrary.LnStrm.

## Final_01 (20 points)
Solved with testing.
Strategy: Built a lazy word stream on top of pg2701_char$strmize. Skips non-word characters recursively and collects word characters into FnList<Character>, converting uppercase to lowercase.

## Final_02 (50 points)
Solved with testing.
Strategy: Converted word stream to array, applied quicksort to sort words alphabetically, counted consecutive equal words, then used mergesort to sort word-count pairs by frequency.

## Final_03 (50 points)
Solved with testing.
Strategy: Used Assign08_02 (open addressing hashmap) to count word occurrences, then mergesorted the resulting list by frequency.

## Final_04 (50 points)
Solved with testing.
Strategy: Used a BST map to count word occurrences, collected results via inorder traversal, then mergesorted by frequency.

## Final_05 (50 points)
Solved with testing.
Strategy: Implemented n-way merge using MyPQueueArray to always pick the minimum head. Used 100-way mergesort by splitting the list into 100 buckets, recursively sorting each, then merging.

## Final_06 (50 bonus points)
Solved with testing.
Strategy: Implemented insertion sort using only recursion and no loops. Two recursive helper functions: insertSort iterates through the array, and insert places each element in its correct position.
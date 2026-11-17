# Public Library System

Final project for the Data Structures course (2026.2), Theme 09.

**Students:** Gustavo Santos Andrade and Guilherme Oliveira Cruz  
**Class:** T1 - Morning  
**Professor:** Karina de Carvalho Leite

## About

A Java console application for managing a public library. The project implements three data structures without Java Collections:

- Binary Search Tree (BST): book records indexed by ISBN, with insertion, search, removal, and three traversals.
- Queue: reservations for books that are currently borrowed, served in first-in, first-out order.
- Stack: returned books waiting to be put back on the shelves, processed in last-in, first-out order.

Each book has its own reservation queue. When a book is returned, it goes into the stack. When the next returned book is processed, it becomes available or is assigned to the first reader in its reservation queue.

## Run in IntelliJ IDEA

1. Open the `BibliotecaPublica` directory in IntelliJ IDEA.
2. Set `src` as **Sources Root** if IntelliJ does not recognize it automatically.
3. Open `src/Main.java` and run `Main.main()`.

Requires JDK 17 or newer. No external dependencies.

From a terminal, you can also use:

```bash
javac src/*.java
java -cp src Main
```

## Notes

- Data is kept in memory while the program is running.
- ISBN is stored as text to preserve leading zeros.
- A book with an active loan, reservations, or a pending return cannot be removed from the catalog.
- Both team members must have the project available on their GitHub accounts, as required by the assignment.

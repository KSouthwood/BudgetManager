# Budget Manager
A Java project from JetBrains Academy ([hyperskill.org](https://hyperskill.org))

## About
Not to sound overly serious, but it’s very important to manage your budget. This implies analyzing your expenses and
estimating the income, which sometimes may be difficult to do yourself. Luckily, technology is there to assist: you can
create your own personal budget manager program that counts the ins and outs and helps control the finances.

## Learning Outcomes
You will create a very applicable product, and in the meantime also get acquainted
with the basic concepts of programming in Java. You will deal with the widest
variety of tools: work with strings, perform mathematical calculations, sort the
data and work with files.

### Stage 1: [Count my money](https://hyperskill.org/projects/76/stages/421/implement)
_Read your purchases from the console and print the total amount._

First, let’s implement the counting of your purchases. It’s much easier to analyze when your expenses are presented as
a list. Read data from the console and at the end show the list of all purchases and its total amount.
It should be displayed as follows: `Total: $23.00`

Your program should process every line the user inputs. To end the input, the user should type End-of-file symbol that
tells your operating system that no more input will be provided. It's `Ctrl+D` on Linux and Mac and `Ctrl+Z` on Windows.

### Stage 2: [Make a menu](https://hyperskill.org/projects/76/stages/422/implement)
_A menu is like the face of an application. At this stage, your task is to make
a user-friendly menu for your program._

Let's make your application more convenient. Only counting the expenses is a little sad, right?

To make your application flexible and functional, add a menu that consists of 4 items.

1. **Add Income**. We must track both our expenses and our income. When this item is selected, the program should ask
to enter the amount of income.
2. **Add Purchase**. This item should add a purchase to the list. You have implemented this feature in the previous stage.
3. **Show the list of purchases**. This menu item should display a list of all expenses and incomes in the order they were made.
4. **Balance**. Show the balance.
5. **Exit**. Exit the program. Make this item under number 0, not number 5.

Notice, that the amount of remaining money cannot be negative. In this case, make the balance equal to $0.

When displaying the price or the total amount, print 2 numbers after the point.
Example: `$14.20`

### Stage 3: [Oh the things you can buy](https://hyperskill.org/projects/76/stages/423/implement)
_Make your manager smarter: create categories for your purchases to better analyze
the user's spending habits._

To better control the expenses, we need to categorize our purchases. It helps to see how exactly your budget is
distributed: you may be actually quite surprised!

Implement a function that assigns a purchase to a specific category. The program should have the following categories:
`Food`, `Clothes`, `Entertainment`, and `Other`.

The function allows you to output the shopping list by type. After selecting the action of showing the list of expenses,
offer to show either a certain category or a general list. At the end, print the total amount of purchases that are on
the list.

### Stage 4: [Memorable purchases](https://hyperskill.org/projects/76/stages/424/implement)
_Planning the budget is a lasting activity: enable your program to keep the 
history of expenses over time._

What's the point of counting the money if the results are lost and forgotten once you close the program? To allow for
some long-term budget planning, we need to save purchases to file. Add items Save and Load to the menu. `Save` should
save all purchases to the file. `Load` should load all purchase from a file. Use the name `purchases.txt` to store
purchases.

### Stage 5: [Analyzer](https://hyperskill.org/projects/76/stages/425/implement)
_Teach your program to perform a concise analysis of income and spendings. Now
that's what we call "budget planning"!_

Do you know how much money you spend on food? On entertainment? it's quite interesting to know since the main purpose of
this application is to analyze your expenses. Let's implement this feature!

First, add the `Analyze` item to the menu. This way you will request an analysis of your purchases. Once this item is
called, you need to offer a way to sort the purchases.

There are three of them:
1) **Sort All** - sort the entire shopping list and display it so that the most expensive purchases are at the top of
the list.
2) **Sort By Type** - show which category eats the most money. If a category has no purchases in it the total sum should
be $0.
3) **Sort Certain Type** - same as **Sort All**, but for a specific category.
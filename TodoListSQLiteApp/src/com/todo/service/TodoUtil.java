package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Create item]\n" + "Category > ");
		
		category = sc.next();
		
		System.out.print("Title >");
		title = sc.next();
		if (l.isDuplicate(title)) {
			System.out.println("Duplicate titles not allowed!");
			return;
		}
		
		System.out.print("Description >");
		sc.nextLine();
		desc = sc.nextLine();
		
		System.out.print("Due date >");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(category,title, desc, due_date);
		t.setIs_completed(0);
		System.out.println();
		if(l.addItem(t)>0) {
			System.out.println("Item added~");
		}
	}

	public static void deleteItem(TodoList l) {
		int no; //번호 입력
		int val=0; //item 번호가 말이 되는지 확인용
		String yn; //yes or no 
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Delete Item]\n" + "The item number to remove > ");
		
		no = sc.nextInt();
		
		for(TodoItem item : l.getList()) {
			if(item.getId()==no) {
				System.out.println(item.toString());
				val=1;
				break;
			}
		}
		
		if (val==1) {
			System.out.print("Delete this item?(y/n) > ");
			yn=sc.next();
			
			if(yn.equals("y")) {
				if(l.deleteItem(no)>0) {
					System.out.println();
					System.out.println("Item Deleted~");
				}
			}
		}
		
		else {
			System.out.println("Invalid number!");
			return;
		}
		
	}

	public static void updateItem(TodoList l) {
		int no; //수정할 item 번호 입력
		int val=0; //item 번호가 말이 되는지 확인용
		String new_title, new_category, new_desc, new_due_date; //새 카테고리, 설명, 마감일자 
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Edit Item]\n" + "The item number to update > ");
		
		no = sc.nextInt();
		
		for(TodoItem item : l.getList()) {
			if(item.getId()==no) {
				System.out.println(item.toString());
				val=1;
				break;
			}
		}
		
		if (val==1) {
			System.out.print("Title of the new item > ");
			new_title = sc.next().trim();
			if (l.isDuplicate(new_title)) {
				System.out.println("Existing title!");
				return;
			}
		
			System.out.print("New Category > ");
			sc.nextLine();
			new_category = sc.next();
		
			System.out.print("New Description > ");
			sc.nextLine();
			new_desc = sc.nextLine().trim();
		
			System.out.print("New Due date >");
			new_due_date = sc.next();
			
			TodoItem t = new TodoItem(new_category, new_title, new_desc, new_due_date);
			t.setId(no);
			t.setIs_completed(0);
			if(l.updateItem(t)>0) {
				System.out.println();
				System.out.println("Item updated~");
			}
		}
		
		else {
			System.out.println("Invalid number!");
			return;
		}
	}

	public static void listAll(TodoList l) {
		int num=l.getCount();
		System.out.println("\n[Item List] , " + num + " items\n");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		int num=l.getCount();
		System.out.println("\n[Item List] , " + num + " items\n");
		for(TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	} 
	
	public static void KeyWordFind(TodoList l, String keyword) {
		int count=0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println();
		System.out.println("Found " + count + " item");

	}
	
	public static void KeyWordFindCate(TodoList l, String cate) {
		int count=0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println();
		System.out.println("Found " + count + " item");
	}
	
	public static void PrintCat(TodoList l) {
		int count=0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println();
		System.out.println("A total of " + count + " categories"); 
	}
	
	public static void CompleteItem(TodoList l, int num) {
		for (TodoItem item : l.getList()) {
			if(item.getId()==num) {
				item.setIs_completed(1);
				if(l.completeItem(item)>0) {
					System.out.println();
					System.out.println("Item marked completed~");
				}
				break;
			}
		}
	}
	
	public static void PrintCompletedItem(TodoList l) {
		int count=0;
		for (TodoItem item : l.getListCompleted()) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println();
		System.out.println("Found " + count + " items");
	}

}

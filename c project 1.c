#include<stdio.h>
#include<string.h>
struct customer{
    char name[100];
    char phonenumber[10];
    float usage;
    float bill;
};
    struct customer customers[100];
    int customerscount=0;
void addRecords()
{
    printf("\nEnter Name: ");
    scanf("%99s",customers[customerscount].name);
    printf("Enter Phone Number: ");
    scanf("%14s",customers[customerscount].phonenumber);
    printf("Total Usage(in minutes): ");
    scanf("%f", &customers[customerscount].usage);
    customers[customerscount].bill  = customers[customerscount].usage * 1.3;
    customerscount++; 
    printf("\nRecord added successfully!\n"); 
} 
 
void viewRecords() 
{ 
    printf("\nName\t\t\tPhone Number\t\t\tUsage(min)\t\t\tBill(Rs)\n"); 
    for (int i = 0; i < customerscount; i++) { 
        printf("%s\t\t\t%s\t\t\t%.2f\t\t\t%.2f", customers[i].name, 
               customers[i].phonenumber, customers[i].usage, 
               customers[i]. bill); 
    } 
}
void modifyRecord(char phonenumber[]) 
{ 
    for (int i = 0; i < customerscount; i++) 
    { 
        if (strcmp(customers[i].phonenumber, phonenumber)  == 0) 
        { 
            printf( "\nEnter New Usage (in minutes) For %s: ",  customers[i].name); 
            scanf("%f", &customers[i].usage); 
            customers[i].bill = customers[i].usage * 1; 
            printf("\nRecord modified successfully!\n"); 
            return; 
        } 
    } 
    printf("\nRecord not found!\n"); 
}
void viewPayment(char phonenumber[]) 
{ 
    for (int i = 0; i < customerscount; i++)
     {
        if (strcmp(customers[i].phonenumber, phonenumber)  == 0) 
        { 
            printf("\nTotal Bill for %s: Rs%.2f\n",   customers[i].name,  customers[i].bill); 
            return; 
        } 
    } 
    printf("\nRecord not found!\n"); 
}
void searchRecord(char phonenumber[])
{ 
	printf("\nName\tPhone Number\tUsage(min)\tTotal Bill($)\n"); 
	for (int i = 0; i < customerscount; i++) { 
		if (strcmp(customers[i].phonenumber, phonenumber) == 0) { 
			printf("%s\t%s\t%.2f\t\t%.2f\n", customers[i].name, customers[i].phonenumber, customers[i].usage, customers[i].bill); 
			return; 
		} 
	} 
	printf("\nRecord not found!\n"); 
}
void deleteRecord(char phonenumber[]) { 
    for (int i = 0; i < customerscount; i++) { 
        if (strcmp(customers[i].phonenumber, phonenumber) == 0) { 
            for (int j = i; j < customerscount - 1; j++) { 
                customers[j] = customers[j + 1]; 
            } 
             
            printf("\nRecord deleted successfully!\n"); 
            return; 
        } 
    } 
    printf("\nRecord not found!\n"); 
}
void displayMenu() 
{ 
    printf("\n1. Add New Record\n"); 
    printf("2. View List of Records\n"); 
    printf("3. Modify Record\n"); 
    printf("4. View Payment\n"); 
    printf("6. Search Record\n"); 
    printf("6. Delete Record\n"); 
    printf("7. Exit\n"); 
} 
int main() 
{ 
	int choice; 
	char phoneNumber[15]; 

	while (1)
    { 
		displayMenu(); 
		printf("Enter your choice: "); 
		scanf("%d", &choice);
           
            switch (choice) 
            { 
            case 1: 
                addRecords(); 
                break; 
            case 2: 
                viewRecords(); 
                break; 
            case 3: 
                printf("\nEnter phone number to modify record: "); 
                scanf("%s", phoneNumber); 
                modifyRecord(phoneNumber); 
                break; 
            case 4: 
                printf("\nEnter phone number to view payment: "); 
                scanf("%s", phoneNumber); 
                viewPayment(phoneNumber); 
                break; 
            case 5: 
                printf("\nEnter phone number to search record: "); 
                scanf("%s", phoneNumber); 
                searchRecord(phoneNumber); 
                break; 
            case 6: 
                printf("\nEnter phone number to delete record: "); 
                scanf("%s", phoneNumber); 
                deleteRecord(phoneNumber); 
                break; 
            case 7: 
                return 0; 
            default: 
                printf("\nInvalid choice! Please try again.\n"); 
            } 
            
	    
        
    }

	return 0; 
}





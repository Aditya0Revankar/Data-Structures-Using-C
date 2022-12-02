#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Stack
#define size 10
char stack[size];
int top = -1;

//Stack functions
int isempty();
int isfull();
void push(char);
char pop();
char peek();
void show();

//Postfix expression
char postfix_exp[20];

//Functions
void addme(char);
int isoperand(char);
int precedence(char);
int isnumber(char);

int main()
{
    char ch,popped,raw_exp[50],garbage;
    printf("Enter the Infix Expression: ");
    scanf("%s", raw_exp);

    int len = strlen(raw_exp);
    char input_exp[len];
    strcpy(input_exp,raw_exp);
    strcat(input_exp,")");
    printf("\n\nFinal Input Expression: %s\n\n", input_exp);
    push('(');

    //Iterator Loop
    for(int i = 0; i < strlen(input_exp); i++)
    {
        printf("\n*******************************************\n");
        ch = input_exp[i];
        printf("Character for the loop: %c\n", ch);

        if(isoperand(ch)||isnumber(ch))
        {   //Operand
            addme(ch);
        }
        else
        {   //Operator
            if(ch!=')')
            {
                if(ch == '(')
                    push(ch);
                else
                {
                    Repeat:
                    if(precedence(ch) == precedence(peek()))
                    {
                        addme(pop());
                        push(ch);
                    }
                    else if(precedence(ch) > precedence(peek()))
                    {
                        push(ch);
                    }
                    else if(precedence(ch) < precedence(peek()))
                    {
                        addme(pop());
                        goto Repeat;
                    }
                }
            }
            else if(ch==')')
            {   //Incoming operator is )
                //POP until ) is found
                while(peek()!='(')
                    addme(pop());
                pop();
            }
            else
                exit(0);
        }

        show();
        printf("Postfix Expression: %s\n\n", postfix_exp);
        printf("\n*******************************************\n");
    }

    printf("\n\n\n\n\n\nFinal Postfix Expression: %s\n\n", postfix_exp);
    return 0;
}

void addme(char ch)
{
    int len = strlen(postfix_exp);
    postfix_exp[len] = ch;
}

int precedence(char ch)
{
        int flag;
        if(ch == '+' || ch == '-'){flag = 1;}
        else if(ch == '*' || ch == '/'){flag = 2;}
        else if(ch == '^'){flag = 3;}
        //else if(ch == '('){flag = 1;}
        else return -1;
        return flag;
}

int isoperand(char ch)
{
    return ((ch>='A' && ch<='Z')||(ch>='a' && ch<='z'));
}

int isnumber(char ch)
{
    return (ch>='0' && ch<='9' );
}

int isempty(){
    if(top == -1)
        return 1;
    else
        return 0;
}

int isfull(){
    if(top == (size-1))
        return 1;
    else
        return 0;
}

void push(char ch){
    if(!isfull()){
        top+=1;
        stack[top] = ch;
    }
    else{
        printf("Stack is full.\nCould not push data on stack.\n");
        //exit(0);
    }
}

char pop(){
    if(!isempty()){
        char ch;
        ch = stack[top];
        top-=1;
        return ch;
    }
    else{
        printf("Stack is empty.\nCould not retrieve data from stack");
        //exit(0);
    }
}

char peek(){
    return stack[top];
}

void show(){
    printf("\n\nElements present in the stack are: \n");
    if(!isempty()){
        for(int i = top; i >= 0; i--){
            printf("%d: %c\n", i, stack[i]);
        }
    }
    printf("\n\n");
}


import { OnInit } from '@angular/core';
import { inject } from '@angular/core';
import { Inject } from '@angular/core';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { PizzaService } from '../pizza.service';


const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PIZZA_TOPPINGS: string[] = [
    'chicken', 'seafood', 'beef', 'vegetables',
    'cheese', 'arugula', 'pineapple'
]

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  form!: FormGroup
  toppingsArr!: FormArray
  fb = inject(FormBuilder)
  pizzaSvc = inject(PizzaService)



  Toppings: Array<any> = [
    { name: 'Chicken', value: 'chicken' },
    { name: 'Seafood', value: 'seafood' },
    { name: 'Beef', value: 'beef' },
    { name: 'Vegetables', value: 'vegetables' },
    { name: 'Cheese', value: 'cheese' },
    { name: 'Arugula', value: 'arugula'}, 
    { name: 'Pineapple', value: 'pineapple'}
  ];


  pizzaSize = SIZES[0]

  constructor() { }

  ngOnInit(): void {
  this.toppingsArr = this.fb.array([], [Validators.required])
    this.form = this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      email: this.fb.control<string>('', [Validators.required]),
      pizzaSize: this.fb.control<string>(this.pizzaSize, [Validators.required]),
      base: this.fb.control<string>('', [Validators.required]),
      sauce: this.fb.control<string>('', [Validators.required]),
      toppings: this.toppingsArr,
      comments: this.fb.control<string>('')
    });
  }

   onCheckboxChange(e: Event) {
    const topping = (e.target as HTMLInputElement).value;
   this.toppingsArr.push(new FormGroup([
    new FormControl<string>(topping)
   ]));
   console.warn(this.toppingsArr);
    }


    process() {
      console.warn(this.form.value)

      this.pizzaSvc.placeOrder(this.form.value);
      
    }
  
    
  
  
    updateSize(size: string) {
      this.pizzaSize = SIZES[parseInt(size)]
    }
  


  }



  


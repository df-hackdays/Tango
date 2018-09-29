import { Component } from '@angular/core';
import { NavBarComponent } from './nav-bar/nav-bar.component'
import { Router, NavigationEnd, Routes } from '@angular/router'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Live-Learning-Frontend';
  currentUrl:String;

  constructor(private router:Router) {
  }
}

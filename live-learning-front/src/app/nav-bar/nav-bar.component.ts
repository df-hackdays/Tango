import { Component, OnInit } from '@angular/core';
import { NavBarService } from '../nav-bar.service'

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {
	navbarCollapsed = true;

  constructor(private navBarService: NavBarService) { }

  ngOnInit() {

  }

  getShowNav (): boolean {
  	return this.navBarService.getVisible();
  }

}

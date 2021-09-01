import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, AfterViewInit {

  @ViewChild("errorMsg", {read: ElementRef, static: true})
  errorMessage !: ElementRef;

  constructor(
    private userService: UserService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.userService.isLoggedIn().then(status => {
      if (status) {
        this.errorMessage.nativeElement.innerText = "You can't create a new account when you are logged in.";
        this.redirectAfterFiveSeconds();
      } else {
        this.userService.sign({redirectUri: window.location.origin});
      }
    });
  }

  redirectAfterFiveSeconds(): void {
    setTimeout(() => {
      this.router.navigate(['']).then();
    }, 5000);
  }
}

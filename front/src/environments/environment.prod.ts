export const environment = {
  production: true,
  backendUrl: 'http://prod.gut.codes/api',
  keycloak: {
    config: {
      url: 'http://prod.gut.codes/auth',
      realm: 'gut.codes',
      clientId: 'frontend'
    },
    loadUserProfileAtStartup: false,
    checkLoginIframe: true,
    bearerExcludedUrls: ['/assets']
  }
};

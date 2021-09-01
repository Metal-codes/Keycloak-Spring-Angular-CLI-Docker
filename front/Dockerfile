#Frontend
##Build stage
FROM node:14.17-alpine as node-builder
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
RUN npm run build

##Expose stage
FROM nginx:1.17.1-alpine
COPY --from=node-builder /app/dist/front /var/www/front
EXPOSE 80

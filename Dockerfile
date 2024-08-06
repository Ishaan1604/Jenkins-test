FROM node

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install --build-from-source

COPY . .

EXPOSE 3000

CMD ["node", "server.js"]
